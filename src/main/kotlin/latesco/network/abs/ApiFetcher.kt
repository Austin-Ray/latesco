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

package latesco.network.abs

interface ApiFetcher {

  val name: String
  val apiDomain: String
  val refreshInterval: Long
  var listener: ApiListener?
  val registeredAssetUids: List<Int>
  var uid: Int?

  /**
   * Update all assets register with this ApiFetcher.
   */
  fun update()

  /**
   * Trigger a manual update of a specific asset.
   *
   * This function should not be used often, but the functionality exists
   * regardless.
   */
  fun manualUpdate(assetUid: Int)

  /**
   * Register an Asset with the ApiFetcher so it will be automatically updated.
   */
  fun registerAsset(assetUid: Int)
}