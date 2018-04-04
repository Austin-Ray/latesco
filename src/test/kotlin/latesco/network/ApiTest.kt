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

package latesco.network

import latesco.network.abs.ApiFetcher
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito

class ApiTest {

  private val name1 = "TEST-1"
  private val name2 = "TEST-2"

  private val domain1 = "www.example.com"
  private val domain2 = "www.example.org"

  private val mockFetcher = Mockito.mock(ApiFetcher::class.java)

  @Test
  fun getFetcher() {
    val api = Api(0, name1, domain1)
    val apiWithFetcher = Api(1, name2, domain2, mockFetcher)

    assertNull(api.fetcher)
    assertEquals(mockFetcher, apiWithFetcher.fetcher)
  }

  @Test
  fun setFetcher() {
    val api = Api(0, name1, domain1)
    val apiWithFetcher = Api(1, name2, domain2, mockFetcher)

    val secondMockFetcher = Mockito.mock(ApiFetcher::class.java)

    assertNull(api.fetcher)
    assertNotNull(apiWithFetcher.fetcher)

    api.fetcher = secondMockFetcher
    apiWithFetcher.fetcher = secondMockFetcher

    assertNotNull(api.fetcher)
    assertNotNull(apiWithFetcher.fetcher)

    assertNotEquals(mockFetcher, apiWithFetcher.fetcher)
  }

  @Test
  fun getApiUid() {
    val api = Api(0, name1, domain1)
    val apiWithFetcher = Api(1, name2, domain2, mockFetcher)

    assertEquals(0, api.apiUid)
    assertEquals(1, apiWithFetcher.apiUid)
  }

  @Test
  fun getApiName() {
    val api = Api(0, name1, domain1)
    val apiWithFetcher = Api(1, name2, domain2, mockFetcher)

    assertEquals(name1, api.apiName)
    assertEquals(name2, apiWithFetcher.apiName)
  }

  @Test
  fun getDomain() {
    val api = Api(0, name1, domain1)
    val apiWithFetcher = Api(1, name2, domain2, mockFetcher)

    assertEquals(domain1, api.domain)
    assertEquals(domain2, apiWithFetcher.domain)
  }
}