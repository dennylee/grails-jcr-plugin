import com.p4f.jcr.JcrPersistentServiceImpl
import com.p4f.jcr.PoolableOcmServiceFactory
import grails.util.GrailsUtil
import org.apache.commons.pool.impl.GenericObjectPool
import org.apache.jackrabbit.commons.JcrUtils
import org.apache.jackrabbit.ocm.mapper.Mapper
import org.apache.jackrabbit.ocm.mapper.impl.annotation.AnnotationMapperImpl
import org.apache.jackrabbit.ocm.reflection.ReflectionUtils

import javax.jcr.Repository
import javax.jcr.SimpleCredentials

class GrailsJcrPluginGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.1 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    def title = "Grails JCR Plugin" // Headline display name of the plugin
    def author = "EA Pulse - Team Black"
    def authorEmail = "dlee@contractor.ea.com"
    def description = '''\
A grails plugin which is able to query against JCR repository.  The plugin will have the ability to use other cache
providers and the sessions communication to JCR repository is pooled.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/grails-jcr-plugin"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
//    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
//    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
//    def scm = [ url: "http://svn.codehaus.org/grails-plugins/" ]

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
    }

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)

        //load necessary configuration
        loadConfig(application.config)

    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    def onShutdown = { event ->
        // TODO Implement code that is executed when the application shuts down (optional)
    }

    private ConfigObject loadConfig(config) {
        def classLoader = new GroovyClassLoader(getClass().classLoader)

        // merge plugin's config
        config.merge(new ConfigSlurper(GrailsUtil.environment).parse(classLoader.loadClass('Config')).merge(config))

        // merge plugin's cache config
        config.merge(new ConfigSlurper(GrailsUtil.environment).parse(classLoader.loadClass('GrailsJcrPluginCacheConfig')).merge(config))
    }

    /**
     * Helper method to create the object pool to be used
     *
     * @param config datasource config
     * @return The object pool
     */
    private GenericObjectPool createObjectPool(def config) {
        SimpleCredentials creds = new SimpleCredentials(config.username, config.password?.toCharArray())
        Repository repo = JcrUtils.getRepository(config.host + config.path)

        PoolableOcmServiceFactory ocmServiceFactory = new PoolableOcmServiceFactory(
                repo, loadObjectMappings(), creds, config.workspace)

        GenericObjectPool pool = new GenericObjectPool(
                ocmServiceFactory,
                config.properties.maxActive ?: GenericObjectPool.DEFAULT_MAX_ACTIVE,
                GenericObjectPool.WHEN_EXHAUSTED_BLOCK,
                config.properties.maxWait ?: GenericObjectPool.DEFAULT_MAX_WAIT,
                config.properties.maxIdle ?: GenericObjectPool.DEFAULT_MAX_IDLE
        )

        return pool
    }

    /**
     * Reads and loads the common object mappings and external mappings.
     */
    private Mapper loadObjectMappings() {
        // load plugin's common object mappings.  The expected file should be named 'CommonObjectMappingConfig'
        def classLoader = new GroovyClassLoader(getClass().classLoader)
        def config = new ConfigSlurper().parse(classLoader.loadClass('CommonObjectMappingConfig'))
        List<Class> commonClasses = new ArrayList<Class>()
        commonClasses.addAll(config.mapping.common as List)

        // load external object mappings.  The expected file should be named 'JcrObjectMappingConfig'
        try {
            def externalConfig = new ConfigSlurper(GrailsUtil.environment).parse(classLoader.loadClass('*JcrObjectMappingConfig'))
            commonClasses.addAll(externalConfig?.mapping.objects as List)
        } catch (ClassNotFoundException cnfe) {
            println "No JcrObjectMappingConfig.groovy found."
        }

        // TODO: I'm wondering if it's because this is a groovy class instead of java class
        ReflectionUtils.setClassLoader(Thread.currentThread().getContextClassLoader());
        Mapper mapper = new AnnotationMapperImpl(commonClasses)

        return mapper
    }
}
