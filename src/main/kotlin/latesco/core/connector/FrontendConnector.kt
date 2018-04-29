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
import java.math.BigDecimal
import java.util.Calendar
import java.util.Date

/**
 * Stub class for the Frontend Connector
 */
object FrontendConnector {
  lateinit var latesco: Latesco
  private val cal = Calendar.getInstance()

  fun getUserQuant(userUid: Int, assetUid: Int) : BigDecimal {
    return latesco.fetchUserQuantity(userUid, assetUid)
  }

  fun setUserQuant(userUid: Int, assetUid: Int, newQuant: BigDecimal) {
    latesco.updateUserQuantity(userUid, assetUid, newQuant)
  }

  fun getCurrentPrice(assetUid: Int, apiUid: Int) : PriceRecord {
    return latesco.getCurrentPrice(assetUid, apiUid)
  }

  fun getPricesBetweenDate(assetUid: Int, apiUid: Int, startDate: Date, endDate: Date) : List<PriceRecord> {
    return latesco.getBetweenDate(assetUid, apiUid, startDate, endDate)
  }

  fun getPricesFromPastDay(assetUid: Int, apiUid: Int) : List<PriceRecord> {
    val dates = getStartAndDate(Calendar.DATE, -1)
    return getPricesBetweenDate(assetUid, apiUid, dates[0], dates[1])
  }

  fun getPricesFromPastWeek(assetUid: Int, apiUid: Int) : List<PriceRecord> {
    val dates = getStartAndDate(Calendar.DATE, -7)
    return getPricesBetweenDate(assetUid, apiUid, dates[0], dates[1])
  }

  fun getPricesFromPastMonth(assetUid: Int, apiUid: Int) : List<PriceRecord> {
    val dates = getStartAndDate(Calendar.MONTH, -1)
    return getPricesBetweenDate(assetUid, apiUid, dates[0], dates[1])
  }

  fun getPricesFromPast3Months(assetUid: Int, apiUid: Int) : List<PriceRecord> {
    val dates = getStartAndDate(Calendar.MONTH, -3)
    return getPricesBetweenDate(assetUid, apiUid, dates[0], dates[1])
  }

  fun getPricesFromPast6Months(assetUid: Int, apiUid: Int) : List<PriceRecord> {
    val dates = getStartAndDate(Calendar.MONTH, -6)
    return getPricesBetweenDate(assetUid, apiUid, dates[0], dates[1])
  }

  fun getPricesFromPastYear(assetUid: Int, apiUid: Int) : List<PriceRecord> {
    val dates = getStartAndDate(Calendar.YEAR, -1)
    return getPricesBetweenDate(assetUid, apiUid, dates[0], dates[1])
  }

  fun getStartAndDate(calMode: Int, delta: Int) : List<Date> {
    val end = Date(System.currentTimeMillis())

    cal.time = end
    cal.add(calMode, delta)

    val start = cal.time

    return listOf(start, end)
  }
}
