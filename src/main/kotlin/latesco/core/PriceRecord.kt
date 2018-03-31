package latesco.core

import java.math.BigDecimal

/**
 * Representation of an asset's price from a specific API at a given time.
 *
 * @param assetUid      latesco.core.Asset unique identifier which corresponds to this price record.
 * @param apiUid        API unique identifier which corresponds to this price record.
 * @param timestamp     Timestamp when the price was fetched.
 * @param price         Price of the asset from a given API.
 */
data class PriceRecord(val assetUid: Long, val apiUid: Long, val timestamp: Long, val price: BigDecimal)