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

/**
 * An API representation for Latesco it manage.
 *
 * All logic for retrieving the prices is done through the Fetcher. The rationale
 * for this choice is Latesco can still have memory of a remove API without needing
 * the code. This is useful for a more contextual history, where APIs are added or
 * removed. No lose of information will occur.
 *
 * @param apiUid    UID
 * @param apiName   Human readable name
 * @param domain    Domain that the API retrieves information from (used for uniqueness)
 * @constructor     Create an API without a fetcher (this can happen for a multitude of reasons)
 */
data class Api(val apiUid: Long, val apiName: String, val domain: String) {
    var fetcher : ApiFetcher? = null

    /**
     * Initialize an API where the fetcher is already known.
     *
     * @param apiUid        Unique identifier
     * @param apiName       Human readable name
     * @param domain        Domain that the API retrieves information from.
     * @param fetcher       ApiFetcher for retrieving pricing information
     */
    constructor(apiUid: Long, apiName: String, domain: String, fetcher: ApiFetcher) : this(apiUid, apiName, domain) {
        this.fetcher = fetcher
    }
}
