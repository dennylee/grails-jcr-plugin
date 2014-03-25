// default settings, can be changed based on different environments
grailsJcrPluginDataSource {
    host = "http://localhost:4502"
    path = "/crx/server"
    username = "admin"
    password = "admin"
    workspace = "crx.default"
    properties {
        maxActive = 10   // max number of objects that can be borrowed from pool at one time
        maxIdle = 8 // max number of idle objects in pool (ms)
        maxWait = 0
        minEvictableIdleTimeMillis = 300000
        timeBetweenEvictionRunsMillis = 60000
    }
}

// environment specific settings
environments {
    development {
        grailsJcrPluginDataSource {
            host = "http://bur2-d1036236.eac.ad.ea.com:4502"
        }
    }
}
