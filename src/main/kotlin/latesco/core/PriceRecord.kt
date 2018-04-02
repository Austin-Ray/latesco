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