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

import latesco.core.Asset
import org.junit.Test

import org.junit.Assert.*

class AssetTest {
    private val assetUid    = 0L
    private val assetName   = "Bitcoin"
    private val assetSymbol = "BTC"

    private val asset    = Asset(assetUid, assetName, assetSymbol)

    @Test
    fun getUid() {
        assertEquals(assetUid, asset.uid)
    }

    @Test
    fun getName() {
        assertEquals(assetName, asset.name)
    }

    @Test
    fun getSymbol() {
        assertEquals(assetSymbol, asset.symbol)
    }

    @Test
    fun equals() {
        val assetDupe = Asset(assetUid, assetName, assetSymbol)
        assertEquals(assetDupe, asset)
    }
}