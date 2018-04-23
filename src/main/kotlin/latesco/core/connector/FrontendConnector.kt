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

package latesco.core.connector

import latesco.core.Latesco
import latesco.core.data.PriceRecord
import java.util.Calendar
import java.util.Date

/**
 * Stub class for the Frontend Connector
 */
object FrontendConnector {
  lateinit var latesco: Latesco

  private val cal = Calendar.getInstance()

  fun getPricesBetweenDate(assetUid: Int, apiUid: Int, startDate: Date, endDate: Date) : List<PriceRecord> {
    return latesco.getBetweenDate(assetUid, apiUid, startDate, endDate)
  }

  fun getPricesFromPastWeek(assetUid: Int, apiUid: Int) : List<PriceRecord> {
    val end = Date(System.currentTimeMillis())

    cal.time = end
    cal.add(Calendar.DATE, -7)

    val start = cal.time

    return getPricesBetweenDate(assetUid, apiUid, start, end)
  }

  fun getPricesFromPastMonth(assetUid: Int, apiUid: Int) : List<PriceRecord> {
    val end = Date(System.currentTimeMillis())

    cal.time = end
    cal.add(Calendar.MONTH, -1)

    val start = cal.time

    return getPricesBetweenDate(assetUid, apiUid, start, end)
  }

  fun getPricesFromPast3Months(assetUid: Int, apiUid: Int) : List<PriceRecord> {
    val end = Date(System.currentTimeMillis())

    cal.time = end
    cal.add(Calendar.MONTH, -3)

    val start = cal.time

    return getPricesBetweenDate(assetUid, apiUid, start, end)
  }

  fun getPricesFromPast6Months(assetUid: Int, apiUid: Int) : List<PriceRecord> {
    val end = Date(System.currentTimeMillis())

    cal.time = end
    cal.add(Calendar.MONTH, -6)

    val start = cal.time

    return getPricesBetweenDate(assetUid, apiUid, start, end)
  }

  fun getPricesFromPastYear(assetUid: Int, apiUid: Int) : List<PriceRecord> {
    val end = Date(System.currentTimeMillis())

    cal.time = end
    cal.add(Calendar.YEAR, -1)

    val start = cal.time

    return getPricesBetweenDate(assetUid, apiUid, start, end)
  }
}
