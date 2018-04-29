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

package latesco.db.def

/**
 * This file contains a few SQL keywords to make writing the SQL strings
 * easier, reduce redundancy, and improve readability.
 */

const val SELECT  = "SELECT"
const val FROM    = "FROM"
const val ALL     = "*"

const val INSERT = "INSERT INTO"
const val VALUES = "VALUES"

const val WHERE   = "WHERE"
const val BETWEEN = "BETWEEN"

const val RETURN  = "RETURNING"

// Logic expressions
const val AND     = "AND"
const val OR      = "OR"
const val NOT     = "NOT"

// Conditionals
const val GTE     = ">="
const val GT      = ">"
const val LTE     = "<="
const val LT      = "<"
const val IS      = "="
