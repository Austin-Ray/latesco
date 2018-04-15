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

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

const val coinMarketApi = "https://api.coinmarketcap.com/v1/"

interface CoinMarketCapService {
  @GET("ticker/")
  fun getTickerAll() : Call<List<ApiResponse>>

  @GET("ticker/{id}")
  fun getTickerSpecific(@Path("id") id: String) : Call<List<ApiResponse>>
}

