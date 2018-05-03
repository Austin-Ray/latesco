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

package latesco.db.def

import latesco.core.data.Asset
import latesco.core.data.PriceRecord
import latesco.db.abs.Database
import latesco.network.Api
import java.math.BigDecimal
import java.sql.Connection
import java.sql.Timestamp
import java.util.*

/**
 * The referential database implementation for Latesco.
 */
class DefaultDatabase(private val conn: Connection) : Database {

  override fun insertPriceEntry(priceEntry: PriceRecord) {
    val sql =
        "$INSERT $TABLE_PRICE " +
        "$VALUES (?, ?, ?, ?);"

    val statement = conn.prepareStatement(sql)

    statement.setTimestamp(1, priceEntry.timestamp)
    statement.setInt(2, priceEntry.assetUid)
    statement.setInt(3, priceEntry.apiUid)
    statement.setBigDecimal(4, priceEntry.price)

    statement.execute()
  }

  override fun insertAsset(assetName: String, assetSymbol: String): Int {
    val sql =
        "$INSERT $TABLE_ASSET ($TABLE_ASSET_NAME, $TABLE_ASSET_SYMBOL)" +
        "$VALUES (?, ?) " +
        "$RETURN $TABLE_ASSET_ID;"

    val statement = conn.prepareStatement(sql)

    statement.setString(1, assetName)
    statement.setString(2, assetSymbol)

    val hasResult = statement.execute()

    if (hasResult) {
      val rs = statement.resultSet
      if (rs.next()) {
        return rs.getInt(TABLE_ASSET_ID)
      }
    }

    throw Exception("SQL STATEMENT FAILED")
  }

  override fun insertApi(apiName: String, domain: String): Int {
    val sql =
        "$INSERT $TABLE_API ($TABLE_API_NAME, $TABLE_API_DOMAIN) " +
        "$VALUES (?, ?) " +
        "$RETURN $TABLE_API_ID"

    val statement = conn.prepareStatement(sql)

    statement.setString(1, apiName)
    statement.setString(2, domain)

    val hasResult = statement.execute()

    if (hasResult) {
      val rs = statement.resultSet

      if (rs.next()) {
        return rs.getInt(TABLE_API_ID)
      }
    }

    throw Exception("SQL STATEMENT FAILED")
  }

  override fun updateUserQuantity(userUid: Int, assetUid: Int, newQuantity: BigDecimal) {
    val sql = "$INSERT $TABLE_USER ($TABLE_USER_TIMESTAMP, $TABLE_USER_UID, $TABLE_USER_AID, $TABLE_USER_QUANT)" +
        " $VALUES (?, ?, ?, ?);"

    val statement = conn.prepareStatement(sql)

    statement.setTimestamp(1,   Timestamp(System.currentTimeMillis()))
    statement.setInt(2,         userUid)
    statement.setInt(3,         assetUid)
    statement.setBigDecimal(4,  newQuantity)

    statement.execute()
  }

  override fun fetchAsset(assetUid: Int): Asset {
    val sql =
        "$SELECT $ALL" +
        "$FROM $TABLE_ASSET " +
        "$WHERE $TABLE_ASSET_ID $IS ?"

    val statement = conn.prepareStatement(sql)

    statement.setInt(1, assetUid)

    val hasResult = statement.execute()

    if (hasResult) {
      val rs = statement.resultSet

      if (rs.next()) {
        return Asset(rs.getInt(TABLE_ASSET_ID), rs.getString(TABLE_ASSET_NAME), rs.getString(TABLE_ASSET_SYMBOL))
      }
    }

    throw Exception("SQL call failed")
  }

  override fun fetchApi(apiUid: Int): Api {
    val sql =
        "$SELECT $ALL" +
            "$FROM $TABLE_API " +
            "$WHERE $TABLE_API_ID $IS ?"

    val statement = conn.prepareStatement(sql)

    statement.setInt(1, apiUid)

    val hasResult = statement.execute()

    if (hasResult) {
      val rs = statement.resultSet

      if (rs.next()) {
        return Api(rs.getInt(TABLE_API_ID), rs.getString(TABLE_API_NAME), rs.getString(TABLE_API_DOMAIN))
      }
    }

    throw Exception("SQL call failed")
  }

  override fun fetchCurrentPrice(assetUid: Int, apiUid: Int): PriceRecord {
    val sql =
        "$SELECT $ALL " +
        "$FROM $TABLE_PRICE " +
        "$WHERE $TABLE_PRICE_ASSET_ID $IS $assetUid $AND $TABLE_PRICE_API_ID $IS $apiUid " +
        "ORDER BY $TABLE_PRICE_TIMESTAMP " +
        "DESC LIMIT 1"

    val statement = conn.prepareCall(sql)

    val hasResult = statement.execute()

    if (hasResult) {
      val rs = statement.resultSet

      if (rs.next()) {
        return PriceRecord(assetUid, apiUid, rs.getTimestamp(TABLE_PRICE_TIMESTAMP),
            rs.getBigDecimal(TABLE_PRICE_PRICE))
      }
    }

    throw Exception("SQL call failed")
  }

  override fun fetchPriceInterval(assetUid: Int, apiUid: Int, start: Date, end: Date): List<PriceRecord> {
    val sql =
        "$SELECT $ALL " +
        "$FROM $TABLE_PRICE " +
        "$WHERE $TABLE_PRICE_ASSET_ID $IS ? " +
            "$AND $TABLE_PRICE_API_ID $IS ? " +
            "$AND $TABLE_PRICE_TIMESTAMP $GTE ? " +
            "$AND $TABLE_PRICE_TIMESTAMP $LTE ?"

    val statement = conn.prepareStatement(sql)

    statement.setInt(1, assetUid)
    statement.setInt(2, apiUid)
    statement.setTimestamp(3, Timestamp(start.time))
    statement.setTimestamp(4, Timestamp(end.time))

    val hasResult = statement.execute()

    if (hasResult) {
      val rs = statement.resultSet

      val list = mutableListOf<PriceRecord>()

      while (rs.next()) {
        list.add(PriceRecord(assetUid, apiUid, rs.getTimestamp(TABLE_PRICE_TIMESTAMP),
            rs.getBigDecimal(TABLE_PRICE_PRICE)))
      }

      return list
    }

    throw Exception("SQL call failed")
  }

  override fun fetchUserAssetQuantity(userUid: Int, assetUid: Int): BigDecimal {
    val sql =
        "$SELECT $TABLE_USER_QUANT " +
        "$FROM $TABLE_USER " +
        "$WHERE $TABLE_USER_UID $IS ? $AND $TABLE_USER_AID $IS ? " +
            "ORDER BY $TABLE_USER_TIMESTAMP DESC LIMIT 1"

    val statement = conn.prepareStatement(sql)

    statement.setInt(1, userUid)
    statement.setInt(2, assetUid)

    val hasResult = statement.execute()

    if (hasResult) {
      val rs = statement.resultSet

      if (rs.next()) {
        return rs.getBigDecimal(TABLE_USER_QUANT)
      }
    }

    throw Exception("SQL call failed")
  }

  override fun fetchAllAssetUids(): List<Int> {
    val sql =
        "$SELECT $TABLE_ASSET_ID " +
        "$FROM $TABLE_ASSET"

    val statement = conn.prepareCall(sql)
    val hasResults = statement.execute()

    val list = mutableListOf<Int>()

    if (hasResults) {
      val results = statement.resultSet

      while (results.next()) {
        list.add(results.getInt(TABLE_ASSET_ID))
      }

      return list
    }

    throw Exception("SQL call failed")
  }

  override fun fetchAllApiUids(): List<Int> {
    val sql =
        "$SELECT $TABLE_API_ID " +
        "$FROM $TABLE_API"

    val statement = conn.prepareCall(sql)
    val hasResults = statement.execute()

    val list = mutableListOf<Int>()

    if (hasResults) {
      val results = statement.resultSet

      while (results.next()) {
        list.add(results.getInt(TABLE_API_ID))
      }

      return list
    }

    throw Exception("SQL call failed")
  }

  override fun allAssetUidsForUser(uid: Int): List<Int> {
    val sql =
        "$SELECT DISTINCT $TABLE_USER_AID " +
        "$FROM $TABLE_USER " +
        "$WHERE $TABLE_USER_UID $IS $uid;"

    val statement = conn.prepareCall(sql)
    val hasResults = statement.execute()

    val list = mutableListOf<Int>()

    if (hasResults) {
      val results = statement.resultSet

      while (results.next()) {
        list.add(results.getInt(TABLE_USER_AID))
      }

      return list
    }

    throw Exception("SQL call failed")
  }
}
