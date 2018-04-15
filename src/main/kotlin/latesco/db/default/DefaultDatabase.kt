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

package latesco.db.default

import latesco.core.data.Asset
import latesco.core.data.PriceRecord
import latesco.db.abs.Database
import java.math.BigDecimal
import java.util.*

/**
 * TODO: Add implementation.
 *
 * This is a stub class to setup calls.
 */
class DefaultDatabase : Database {
  override fun insertPriceEntry(priceEntry: PriceRecord) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun insertAsset(assetName: String, assetSymbol: String): Long {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun insertApi(apiName: String): Long {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun updateUserQuantity(userUid: Long, assetUid: Long, newQuantity: Long) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun fetchAsset(assetUid: Long): Asset {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun fetchCurrentPrice(assetUid: Long, apiUid: Long): PriceRecord {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun fetchPriceInterval(assetUid: Long, apiUid: Long, start: Date, end: Date): List<PriceRecord> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun fetchUserAssetQuantity(userUid: Long, assetUid: Long): BigDecimal {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun fetchAllAssetUids(): List<Long> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun fetchAllApiUids(): List<Long> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}