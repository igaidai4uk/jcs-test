Small web application that tests Java Caching System with TCP Lateral Cache.

The flow is the following:
1. Deploy the application into several tomcats.
2. Update in cache.conf the following parameter:
> jcs.auxiliary.LTCP.attributes.TcpListenerPort - make sure the parameter is different;
3. Start the all tomcats.

To update cache call _/put_ servlet.
To read from cache call _/get_ servlet.
To observe metrics call _/CodahaleMetrics/metrics_


