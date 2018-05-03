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

package latesco.network.coinmarket

import latesco.core.data.Asset
import latesco.core.data.PriceRecord
import latesco.network.abs.ApiFetcher
import latesco.network.abs.ApiListener
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.Timestamp

/**
 * An ApiFetcher implementation for retrieving crypto-currency prices from CoinMarketCap.
 *
 * @param refreshInterval   How often the fetcher should wait before adding a new price entry (in seconds)
 */
class CoinMarketFetcher(override val refreshInterval: Long) : ApiFetcher {

  override var uid: Int? = null

  override val name = "CoinMarketCap"

  override val apiDomain: String
    get() = coinMarketApi

  override var listener: ApiListener? = null

  override val registeredAssetUids: MutableList<Int> = mutableListOf()

  private val service: CoinMarketCapService

  init {
    val rt = Retrofit.Builder()
        .baseUrl(coinMarketApi)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    service = rt.create(CoinMarketCapService::class.java)
  }

  override fun update() {
    if (listener == null) {
      return
    }

    val listener = listener!!

    val response = service.getTickerAll().execute()

    if (response.isSuccessful) {
      val results = response.body()!!

      // For every registered Asset UID, find a match in the response
      registeredAssetUids.forEach { uid ->
        // This is guaranteed to exist since these UIDs are registered from the listener
        val assetId = listener.getAsset(uid).name.toLowerCase().replace(" ", "-")
        val match: ApiResponse? = results.find { it.id == assetId }

        if (match != null) {
          listener.recordPrice(createPriceEntry(uid, match))
        }
      }
    }
  }

  override fun manualUpdate(assetUid: Int) {
    if (listener == null || !registeredAssetUids.contains(assetUid)) {
      return
    }

    val listener = listener!!

    val asset: Asset = listener.getAsset(assetUid)

    // CoinMarketCap uses the lower case name as the id
    val assetId = asset.name.toLowerCase()

    val response = service.getTickerSpecific(assetId).execute()

    if (response.isSuccessful) {
      val result = response.body()

      result?.forEach {
        val record = createPriceEntry(assetUid, it)
        listener.recordPrice(record)
      }
    }
  }

  private fun createPriceEntry(assetUid: Int, result: ApiResponse) : PriceRecord {
    return PriceRecord(assetUid, uid!!, Timestamp(System.currentTimeMillis()), result.price_usd)
  }

  override fun registerAsset(assetUid: Int) {
    registeredAssetUids.add(assetUid)
  }
}