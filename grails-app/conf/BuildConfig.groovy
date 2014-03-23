grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    legacyResolve true // whether to do a secondary resolve on plugin installation, not advised but set here for backwards compatibility
    repositories {
        grailsCentral()
        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        //mavenLocal()
        //mavenCentral()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.
        compile 'javax.jcr:jcr:2.0'
        compile 'org.apache.jackrabbit:jackrabbit-jcr-commons:2.7.4'
        compile 'org.apache.jackrabbit:jackrabbit-ocm:1.5.3'
        compile 'org.apache.jackrabbit:jackrabbit-spi-commons:2.7.4'
        compile 'org.apache.jackrabbit:jackrabbit-jcr2dav:2.7.4'
        compile 'org.apache.jackrabbit:jackrabbit-spi:2.7.4'
        compile 'org.apache.jackrabbit:jackrabbit-jcr2spi:2.7.4'
        compile 'org.apache.jackrabbit:jackrabbit-spi2jcr:2.7.4'
        compile 'org.apache.jackrabbit:jackrabbit-spi2dav:2.7.4'
        compile 'org.apache.jackrabbit:jackrabbit-webdav:2.7.4'
        compile 'org.apache.jackrabbit:jackrabbit-jcr-tests:2.2.8'


        compile 'commons-httpclient:commons-httpclient:3.1'
        compile 'commons-codec:commons-codec:1.2'
        compile 'commons-logging:commons-logging:1.0.4'


        compile 'org.apache.jackrabbit:jackrabbit-core:2.7.4'

//        runtime 'org.apache.jackrabbit:jackrabbit-jca:2.7.4'

//        runtime 'org.apache.jackrabbit:jackrabbit-jcr2dav:2.7.4'
//        runtime 'org.apache.jackrabbit:jackrabbit-jcr-client:2.7.4'
//        runtime 'org.apache.jackrabbit:jackrabbit-spi:2.7.4'
//        runtime 'org.apache.jackrabbit:jackrabbit-webdav:2.7.4'
//        runtime 'org.apache.jackrabbit:jackrabbit-jcr-rmi:2.7.4'
    }

    plugins {
        build(":tomcat:$grailsVersion",
              ":release:2.2.0",
              ":rest-client-builder:1.0.3") {
            export = false
        }

        compile ":cache:1.1.1"
        compile ":cache-ehcache:1.0.0"
    }
}
