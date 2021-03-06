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

import latesco.core.data.Asset
import latesco.core.data.PriceRecord

interface ApiListener {
  /**
   * Tell the Listener to record a new PriceRecord entry
   *
   * @param record    New PriceRecord
   */
  fun recordPrice(record: PriceRecord)

  /**
   * Return the Asset corresponding to a UID.
   *
   * @param uid   UID of Asset
   *
   * @return Asset matching the UID.
   */
  fun getAsset(uid: Int) : Asset
}