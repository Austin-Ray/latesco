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

package latesco.db.abs

import latesco.core.data.Asset
import latesco.core.data.PriceRecord
import java.math.BigDecimal
import java.util.*

/**
 * Interface for a Database implementation.
 *
 * Latesco uses this interface for its database handling. If one would like to use
 * another database implementation instead of the referential one included with
 * Latesco, it must implement this interface.
 */
interface Database {

  /**
   * Inserts a new price record for an asset.
   *
   * Since PriceRecord contains all the relevant information to add to the database
   * it isn't necessary to pass in more information.
   *
   * @param priceEntry    PriceRecord object to be recorded in the database
   */
  fun insertPriceEntry(priceEntry: PriceRecord)

  /**
   * Inserts a new asset into the database and returns a UID.
   *
   * @param assetName     Human-readable name for an asset
   * @param assetSymbol   Symbol that represents that asset
   *
   * @return  Asset UID for asset inserted into the database.
   */
  fun insertAsset(assetName: String, assetSymbol: String): Long

  /**
   * Insert an API into the database and return its UID for archival purposes.
   *
   * This is done so that information can be corroborated even if an API
   * has been removed from the program. Otherwise, a loss of information
   * occurs.
   *
   * @param apiName   Human-readable name
   *
   * @return          UID for the API
   */
  fun insertApi(apiName: String): Long

  /**
   * Update a user's quantity for a given asset.
   *
   * Latesco operates on the assumption that it will be a dependency in a larger
   * piece of software. This way, it trusts that the user UID provide has not been
   * modified maliciously. Otherwise, the program it is incorporated in is being
   * hostile to itself.
   *
   * @param userUid       User UID
   * @param assetUid      Asset UID
   * @param newQuantity   New quantity
   */
  fun updateUserQuantity(userUid: Int, assetUid: Int, newQuantity: BigDecimal)

  /**
   * Fetches an Asset given its UID.
   *
   * This is function is beneficial for cache swaps since the list of asset UIDs
   * is relatively small to keep in memory compared to the entirety of the Asset
   * objects. Furthermore, the primitive nature of using UIDs makes searching
   * faster.
   *
   * @param assetUid  UID of asset
   *
   * @return          Composed Asset object based on database record
   */
  fun fetchAsset(assetUid: Int): Asset

  /**
   * Return the most recent PriceRecord for an asset and API.
   *
   * @param assetUid      Asset UID
   * @param apiUid        API UID
   *
   * @return              Most recent PriceRecord matching asset and API UID.
   */
  fun fetchCurrentPrice(assetUid: Int, apiUid: Int): PriceRecord

  /**
   * Return a list of PriceRecords for a given API's asset prices between an inclusive bound.
   *
   * This is a generic implementation to allow more flexibility in queries. Specific query
   * intervals such as 24hr, 6mon, or 1y are implemented higher up on the call chain.
   *
   * @param assetUid  Asset UID
   * @param apiUid    API UID
   * @param start     Inclusive start date-time of query
   * @param end       Inclusive end date-time of query
   *
   * @return
   */
  fun fetchPriceInterval(assetUid: Int, apiUid: Int, start: Date, end: Date): List<PriceRecord>

  /**
   * Fetch the quantity of a user's asset.
   *
   * @param userUid       User UID
   * @param assetUid      Asset UID
   *
   * @return              Decimal representation of quantity. Some assets can be partial.
   */
  fun fetchUserAssetQuantity(userUid: Int, assetUid: Int): BigDecimal

  /**
   * Return a list of all Asset UIDs.
   *
   * Caching benefits from this function as Latesco can check existing UIDs without reading from the
   * database to verify if cache is incomplete. The result is lower memory usage by using simple
   * primitives and ignoring the extraneous information, which is beneficial if the list of assets
   * is in the order of millions.
   *
   * @return  List of all Asset UIDs.
   */
  fun fetchAllAssetUids(): List<Int>

  /**
   * Return all API UIDs.
   *
   * This function is used on Latesco's startup to match to registered APIs.
   *
   * This is not used for caching as swapping out APIs should never be done.
   *
   * @return  List of all API UIDs.
   */
  fun fetchAllApiUids(): List<Int>
}