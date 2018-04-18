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
    val sql =
        "$INSERT $TABLE_PRICE " +
        "$VALUES (${priceEntry.timestamp}, ${priceEntry.assetUid}, ${priceEntry.apiUid}, ${priceEntry.price});"
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun insertAsset(assetName: String, assetSymbol: String): Long {
    val sql =
        "$INSERT $TABLE_ASSET ($TABLE_ASSET_NAME, $TABLE_ASSET_SYMBOL)" +
        "$VALUES ($assetName, $assetSymbol) " +
        "$RETURN $TABLE_ASSET_ID;"
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun insertApi(apiName: String): Long {
    val sql =
        "$INSERT $TABLE_API ($TABLE_API_NAME)" +
        "$VALUES $apiName" +
        "$RETURN $TABLE_API_ID"
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun updateUserQuantity(userUid: Long, assetUid: Long, newQuantity: Long) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun fetchAsset(assetUid: Long): Asset {
    val sql =
        "$SELECT $ALL" +
        "$FROM $TABLE_API " +
        "$WHERE $TABLE_API_ID $IS $assetUid"
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun fetchCurrentPrice(assetUid: Long, apiUid: Long): PriceRecord {
    val sql =
        "$SELECT TOP 1 $ALL " +
        "$FROM $TABLE_API " +
        "ORDER BY $TABLE_API_ID DESC"
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun fetchPriceInterval(assetUid: Long, apiUid: Long, start: Date, end: Date): List<PriceRecord> {
    val startTimeStamp = start.time
    val endTimeStamp = end.time

    val sql =
        "$SELECT $ALL " +
        "$FROM $TABLE_PRICE" +
        "$WHERE $TABLE_PRICE_TIMESTAMP $GTE $startTimeStamp $AND $TABLE_PRICE_TIMESTAMP $LTE $endTimeStamp"
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun fetchUserAssetQuantity(userUid: Long, assetUid: Long): BigDecimal {
    val sql =
        "$SELECT $TABLE_USER_QUANT, MAX($TABLE_USER_TIMESTAMP) " +
        "$FROM $TABLE_USER" +
        "$WHERE $TABLE_USER_UID $IS $userUid $AND $TABLE_USER_AID $IS $assetUid"
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun fetchAllAssetUids(): List<Long> {
    val sql =
        "$SELECT $TABLE_ASSET_ID " +
        "$FROM $TABLE_ASSET_ID"
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun fetchAllApiUids(): List<Long> {
    val sql =
        "$SELECT $TABLE_API_ID " +
        "$FROM $TABLE_API"
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}