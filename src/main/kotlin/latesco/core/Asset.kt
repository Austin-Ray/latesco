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

/**
 * Object representing an arbitrary asset in Latesco's memory.
 *
 * During initialization, Latesco will read the entire asset list from its database
 * and create a list of latesco.core.Asset objects. This will be Latesco's working memory of how
 * many different types of assets it currently has.
 *
 * When a new asset entry is created, this object will be the last piece created.
 * The UID comes from the database so it is impossible to instantiate an object
 * before then.
 *
 * This object will never store pricing information as the price could be sourced
 * from multiple data points. The prime example is a crypto-currency's price from
 * various exchanges.
 *
 * @param uid       The unique identifier the asset.
 * @param name      The human readable name for an asset.
 * @param symbol    The symbol which represents the asset.
 */
data class Asset(val uid: Long, val name: String, val symbol: String)
