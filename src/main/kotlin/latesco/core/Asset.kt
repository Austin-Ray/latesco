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
