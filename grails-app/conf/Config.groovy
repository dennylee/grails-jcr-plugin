import org.apache.commons.pool.impl.GenericObjectPool

// configuration for plugin testing - will not be included in the plugin zip

log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'

    warn   'org.mortbay.log'
}

// Default object content mapping configuration
grails.jcr.plugin.ocm.strategy = "xml"  // 'xml' or 'annotation'
grails.jcr.plugin.ocm.mapping = []  // a list of either xml files or classes depending on strategy

// Default pooling configuration
grails.jcr.plugin.ocm.pool.timeBetweenEvictionRuns = 60000 // ms
grails.jcr.plugin.ocm.pool.minEvictableIdleTime = 300000 // ms
grails.jcr.plugin.ocm.pool.maxActive = 0    // max number of objects that can be borrowed from pool at one time
grails.jcr.plugin.ocm.pool.maxIdle = 8 // max number of idle objects in pool

// Default repository configuration
grails.jcr.plugin.repo.host = "http://localhost:4502"
grails.jcr.plugin.repo.username = "admin"
grails.jcr.plugin.repo.password = "admin"
grails.jcr.plugin.repo.workspace = "crx.default"