package com.weremain.fund.wrw;

import com.weremain.fund.wrw.support.Settings;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Application;
import java.util.Set;

/**
 * Created by kevin on 11/15/16.
 */

public final class Bootstrap extends Application {
    static final String APPLICATION_ROOT_URL = "/";
    private static final Logger LOG = LoggerFactory.getLogger(Bootstrap.class);

    /**
     * Construct the application and register the resources.
     */
    public Bootstrap()
    {
        //Log we are ready and the url...
        LOG.info("Starting application @ /{}/[RESOURCE]", APPLICATION_ROOT_URL);
        //Force the settings to instantiate
        final Settings settings = Settings.getInstance();
        LOG.info("Environment: {}", settings.getEnvironment());
        LOG.info("Download Path: {}", settings.getDownloadPath());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<Object> getSingletons()
    {
        return Sets.newHashSet();
    }
}
