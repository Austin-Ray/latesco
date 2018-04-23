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
import java.util.Date

class Latesco(private val db: Database) {

  fun getBetweenDate(assetUid: Int, apiUid: Int, startDate: Date, endDate: Date): List<PriceRecord> {
    return db.fetchPriceInterval(assetUid, apiUid, startDate, endDate)
  }

  fun getConnector() : FrontendConnector {
    val connector = FrontendConnector
    connector.latesco = this

    return connector
  }
}