package no.dusken.intern.service;

import org.kantega.jexmec.Services;
import org.kantega.jexmec.store.PluginStoreProvider;

public interface PluginServices extends Services {
    /**
     * Provides access to persistent state so the plugin can store configuration
     * etc.
     * @return the PluginStoreProvider
     */
    PluginStoreProvider getPluginStoreProvider();
}
