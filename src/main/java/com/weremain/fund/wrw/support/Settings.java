package com.weremain.fund.wrw.support;

import com.google.common.base.Splitter;
import com.google.common.io.Closeables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by kevin on 11/15/16.
 */
public class Settings {
    private static final Logger LOG = LoggerFactory.getLogger(Settings.class);
    private static final String ENVIRONMENT_PROPERTY = "environment";
    private static final String DOWNLOAD_PATH_PROPERTY = "downloads";
    private static final String VIEWABLE_RESULTS_PROPERTY = "viewable";
    private static final String DOWNLOADABLE_RESULTS_PROPERTY = "downloadable";
    private static final String VIEWS_PROPERTY = "views";
    private final static String APP_CONFIG = "/application.properties";
    private final static Settings settings = new Settings();
    private String environment;
    private String downloadPath;
    private long viewableResults;
    private long downloadableResults;
    private List<String> views;

    /**
     * This is a singleton
     */
    private Settings()
    {
        InputStream input = null;

        try
        {
            input = Settings.class.getResourceAsStream(APP_CONFIG);

            // load a properties file
            final Properties properties = new Properties();
            properties.load(input);
            LOG.debug("Properties {}", properties.get(ENVIRONMENT_PROPERTY));

            //Set properties
            environment = (String)properties.get(ENVIRONMENT_PROPERTY);
            downloadPath = (String)properties.get(DOWNLOAD_PATH_PROPERTY);
            viewableResults = Long.valueOf((String)properties.get(VIEWABLE_RESULTS_PROPERTY));
            downloadableResults = Long.valueOf((String)properties.get(DOWNLOADABLE_RESULTS_PROPERTY));
            views = Splitter.on(",")
                    .trimResults()
                    .omitEmptyStrings()
                    .splitToList((String)properties.get(VIEWS_PROPERTY));
        }
        catch (IOException x)
        {
            LOG.error("Cannot load application properties", x);
        }
        finally
        {
            Closeables.closeQuietly(input);
        }
    }

    /**
     * Get the singleton instance of settings.
     */
    public static Settings getInstance()
    {
        return settings;
    }

    public String getDownloadPath()
    {
        return this.downloadPath;
    }

    /**
     * Get the downloadable results threshold
     */
    public long getDownloadableThreshold()
    {
        return downloadableResults;
    }

    /**
     * Return the active environment
     */
    public String getEnvironment()
    {
        return this.environment;
    }

    /**
     * Get the viewable results threshold
     */
    public long getViewableThreshold()
    {
        return viewableResults;
    }

    /**
     * Get available views.
     */
    public List<String> getViews()
    {
        //Safeguard if views is null (which it should not be)
        if (views == null)
        {
            return new ArrayList<String>();
        }

        return this.views;
    }
}
