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

import latesco.core.data.Asset
import latesco.network.abs.ApiListener
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito

class CoinMarketFetcherTest {

  @Test
  fun getApiDomain() {
    val fetcher = CoinMarketFetcher(5)
    assertEquals(coinMarketApi, fetcher.apiDomain)
  }

  @Test
  fun getAndSetListener() {
    val mockListener = Mockito.mock(ApiListener::class.java)

    val fetcher = CoinMarketFetcher(0)

    assertNull(fetcher.listener)

    fetcher.listener = mockListener

    assertNotNull(fetcher.listener)
    assertEquals(mockListener, fetcher.listener)
  }

  @Test
  fun getAndAddToRegisteredAssetUids() {
    val fetcher = CoinMarketFetcher(0)

    assertEquals(0, fetcher.registeredAssetUids.size)

    fetcher.registerAsset(0)

    assertEquals(1, fetcher.registeredAssetUids.size)
    assertEquals(0, fetcher.registeredAssetUids[0])
  }

  @Test
  fun update() {
    var counter = 0
    val bitcoinAsset = Asset(0, "Bitcoin", "BTC")
    val ethereum = Asset(1, "Ethereum", "ETH")
    val mockListener = Mockito.mock(ApiListener::class.java)

    Mockito.`when`(mockListener.recordPrice(anyObject())).then { counter++}
    Mockito.`when`(mockListener.getAsset(0)).thenReturn(bitcoinAsset)
    Mockito.`when`(mockListener.getAsset(1)).thenReturn(ethereum)

    val fetcher = CoinMarketFetcher(0)

    fetcher.update()
    assertEquals(0, counter)

    fetcher.listener = mockListener

    fetcher.registerAsset(bitcoinAsset.uid)

    assertEquals(0, counter)
    fetcher.update()
    assertEquals(1, counter)

    fetcher.registerAsset(ethereum.uid)
    fetcher.update()
    assertEquals(3, counter)
  }

  @Test
  fun manualUpdate() {
    var counter = 0
    val bitcoinAsset = Asset(0, "Bitcoin", "BTC")
    val mockListener = Mockito.mock(ApiListener::class.java)

    Mockito.`when`(mockListener.recordPrice(anyObject())).then { counter++}
    Mockito.`when`(mockListener.getAsset(0)).thenReturn(bitcoinAsset)

    val fetcher = CoinMarketFetcher(0)

    assertEquals(0, counter)
    fetcher.manualUpdate(bitcoinAsset.uid)
    fetcher.manualUpdate(-1)
    assertEquals(0, counter)

    fetcher.listener = mockListener
    fetcher.registerAsset(bitcoinAsset.uid)

    fetcher.manualUpdate(bitcoinAsset.uid)
    assertEquals(1, counter)

    fetcher.manualUpdate(-1)
    assertEquals(1, counter)
  }

  @Test
  fun getRefreshInterval() {
    val fetcher = CoinMarketFetcher(5)
    assertEquals(5, fetcher.refreshInterval)
  }
}

private fun <T> anyObject(): T {
  return Mockito.any<T>()
}
