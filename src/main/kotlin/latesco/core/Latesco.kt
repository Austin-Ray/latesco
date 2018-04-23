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
import latesco.core.data.PriceRecord
import latesco.db.abs.Database
import java.math.BigDecimal
import java.util.Date

/**
 * The core controller of Latesco.
 *
 * A lot of these methods are simple passthrough calls from the connector to the database.
 * While this is bad design in the current instance, the initial plan was to include caching
 * and queuing mechanisms, but they were cut due to time crunch.
 */
class Latesco(private val db: Database) {

  fun fetchUserQuantity(userUid: Int, assetUid: Int) : BigDecimal {
    return db.fetchUserAssetQuantity(userUid, assetUid)
  }

  fun updateUserQuantity(userUid: Int, assetUid: Int, newQuantity: BigDecimal) {
    db.updateUserQuantity(userUid, assetUid, newQuantity)
  }

  fun getBetweenDate(assetUid: Int, apiUid: Int, startDate: Date, endDate: Date): List<PriceRecord> {
    return db.fetchPriceInterval(assetUid, apiUid, startDate, endDate)
  }

  fun getConnector() : FrontendConnector {
    val connector = FrontendConnector
    connector.latesco = this

    return connector
  }
}