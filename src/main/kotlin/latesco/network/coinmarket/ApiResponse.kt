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

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

/**
 * Class representing a single crypto-currency response from Latesco. It is modeled directly from the JSON.
 *
 * Since this class is based on JSON, the naming conventions are different.
 */
class ApiResponse(val id: String, val name: String, val symbol: String, val rank: Int, val price_usd: BigDecimal,
    val price_btc: BigDecimal, @SerializedName("24h_volume_usd") val volume_usd: BigDecimal,
    val market_cap_usd: BigDecimal, val available_supply: BigDecimal, val total_supply: BigDecimal,
    val percent_change_1h: Double, val percent_change_24h: Double, val percent_change_7d: Double,
    val last_updated: Long)