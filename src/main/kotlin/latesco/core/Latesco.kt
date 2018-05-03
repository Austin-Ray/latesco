/*
 * This file is part of Latesco.
 *
 * Latesco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Latesco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Latesco.  If not, see <http://www.gnu.org/licenses/>.
 */

package latesco.core

import latesco.core.connector.FrontendConnector
import latesco.core.data.Asset
import latesco.core.data.PriceRecord
import latesco.db.abs.Database
import latesco.network.Api
import latesco.network.abs.ApiFetcher
import latesco.network.abs.ApiListener
import java.math.BigDecimal
import java.util.Date
import java.util.Timer
import java.util.TimerTask



/**
 * The core controller of Latesco.
 *
 * A lot of these methods are simple passthrough calls from the connector to the database.
 * While this is bad design in the current instance, the initial plan was to include caching
 * and queuing mechanisms, but they were cut due to time crunch.
 */
class Latesco(private val db: Database) : ApiListener {

  private val timer = Timer()

  // Private APIs to prevent outside manipulation
  private val privApis  = mutableListOf<Api>()
  private val privAssets  = mutableListOf<Asset>()

  // Public API list to prevent modification of known APIs from outside source
  val apis: List<Api>
    get() = privApis.toList()

  val assets: List<Asset>
    get() = privAssets.toList()

  init {
    val assetUids = db.fetchAllAssetUids()
    assetUids.forEach { privAssets.add(db.fetchAsset(it)) }

    val apiUids = db.fetchAllApiUids()
    apiUids.forEach { privApis.add(db.fetchApi(it)) }
  }

  fun registerApi(api: ApiFetcher) {
    if (privApis.stream().anyMatch { it.apiName == api.name && it.domain == api.apiDomain}) {
      val matchingApi = privApis.find{ it.apiName == api.name && it.domain == api.apiDomain }
      api.uid = matchingApi?.apiUid
      matchingApi?.fetcher = api
      scheduleApiRefresh(api)

      return
    }

    val id = db.insertApi(api.name, api.apiDomain)
    api.uid = id
    privApis.add(Api(id, api.name, api.apiDomain, api))
    scheduleApiRefresh(api)
  }

  private fun scheduleApiRefresh(apiFetcher: ApiFetcher) {
    apiFetcher.listener = this

    timer.scheduleAtFixedRate(object : TimerTask() {
      override fun run() {
        apiFetcher.update()
      }
    }, 0L, apiFetcher.refreshInterval)
  }

  fun insertAsset(name: String, symbol: String) {
    // Exit if asset exists.
    if (privAssets.stream().anyMatch { it ->  it.name == name && it.symbol == symbol }) {
      return
    }

    val id = db.insertAsset(name, symbol)

    privAssets.add(Asset(id, name, symbol))
  }

  fun updateUserQuantity(userUid: Int, assetUid: Int, newQuantity: BigDecimal) {
    db.updateUserQuantity(userUid, assetUid, newQuantity)
  }

  fun getCurrentPrice(assetUid: Int, apiUid: Int) : PriceRecord {
    return db.fetchCurrentPrice(assetUid, apiUid)
  }

  fun getBetweenDate(assetUid: Int, apiUid: Int, startDate: Date, endDate: Date): List<PriceRecord> {
    return db.fetchPriceInterval(assetUid, apiUid, startDate, endDate)
  }

  fun fetchUserQuantity(userUid: Int, assetUid: Int) : BigDecimal {
    return db.fetchUserAssetQuantity(userUid, assetUid)
  }

  fun getConnector() : FrontendConnector {
    val connector = FrontendConnector
    connector.latesco = this

    return connector
  }

  fun getAllAssetIds(uid: Int) : List<Int> {
    return db.allAssetUidsForUser(uid)
  }

  override fun recordPrice(record: PriceRecord) {
    db.insertPriceEntry(record)
  }

  override fun getAsset(uid: Int): Asset {
    return privAssets.first { it.uid == uid }
  }
}