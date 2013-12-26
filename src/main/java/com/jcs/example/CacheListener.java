package com.jcs.example;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author igaidaichuk@cogniance.com
 */
public class CacheListener implements ServletContextListener {

    public CachingService cachingService;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        cachingService = CachingService.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
