// default settings, can be changed based on different environments
grailsJcrPluginDataSource {
    host = "http://localhost:4502"
    path = "/crx/server"
    username = "admin"
    password = "admin"
    workspace = "crx.default"
    properties {
        maxActive = 0   // max number of objects that can be borrowed from pool at one time
        maxIdle = 8 // max number of idle objects in pool (ms)
        minEvictableIdleTimeMillis=300000
        timeBetweenEvictionRunsMillis=60000

        strategy = "xml"  // strategy for mapping POJO from JCR Node: 'xml' or 'annotation'
        mapping = []  // a list of either xml files or classes depending on strategy
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
