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

package latesco.db.default

// Tables
const val TABLE_API   = "api"
const val TABLE_ASSET = "asset"
const val TABLE_PRICE = "price"
const val TABLE_USER  = "user"

// Asset table columns
const val TABLE_ASSET_ID      = "id"
const val TABLE_ASSET_NAME    = "name"
const val TABLE_ASSET_SYMBOL  = "symbol"

// API table columns
const val TABLE_API_ID    = "id"
const val TABLE_API_NAME  = "name"

// Price table columns
const val TABLE_PRICE_TIMESTAMP = "timestamp"
const val TABLE_PRICE_ASSET_ID  = "asset_uid"
const val TABLE_PRICE_API_ID    = "api_uid"
const val TABLE_PRICE_PRICE     = "price"

// User table columns
const val TABLE_USER_TIMESTAMP    = "timestamp"
const val TABLE_USER_UID          = "user_uid"
const val TABLE_USER_AID          = "asset_uid"
const val TABLE_USER_QUANT_CHANGE = "quant_change"
const val TABLE_USER_QUANT        = "quant_total"

