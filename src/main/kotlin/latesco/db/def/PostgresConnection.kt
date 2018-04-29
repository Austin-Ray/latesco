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

import java.sql.Connection
import java.sql.DriverManager
import java.util.Properties

private var isDriverLoaded = false

/**
 * Load the PostgreSQL JDBC driver to connect to the database.
 */
fun loadDriver() {
  Class.forName("org.postgresql.Driver").newInstance()
  isDriverLoaded = true
}

/**
 * Connection to a PostgreSQL database using PostgreSQL's JDBC.
 *
 * @param host      Host's IP or URL
 * @param db        Database name
 * @param user      Username
 * @param password  User's password
 *
 * @return          An SSL connection to a PostgrSQL database
 */
fun getPostgresConnection(host: String, db:String, user: String, password: String) : Connection {
  if (!isDriverLoaded) {
    loadDriver()
  }

  val url = "jdbc:postgresql://$host/$db"

  val props = Properties()

  props.setProperty("user",     user)
  props.setProperty("password", password)
  props.setProperty("ssl",      false.toString())

  return DriverManager.getConnection(url, props)
}
