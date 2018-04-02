package latesco.network.abs

interface ApiFetcher {

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
    fun manualUpdate(assetUid: Long)

    /**
     * Register an Asset with the ApiFetcher so it will be automatically updated.
     */
    fun registerAsset(assetUid: Long)

    /**
     * Register a callback listener with the ApiFetcher.
     *
     * This function will be called when registering an ApiFetcher with Latesco. It
     * should not be used anywhere else.
     */
    fun registerListener(listener: ApiListener)

    /**
     * Return a list of all registered assetUids
     *
     * @return List of registered Uids
     */
    fun getRegisteredUids() : List<Long>

    /**
     * Return the refresh interval, in seconds, for the ApiFetcher
     *
     * @return Refresh invterval in seconds
     */
    fun getRefreshInterval() : Long

    /**
     * Returns the domain of the ApiFetcher.
     *
     * This is used for ensuring uniqueness of an ApiFetcher during initialization.
     */
    fun getApiDomain() : String
}