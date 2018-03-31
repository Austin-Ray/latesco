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