import com.ea.core.JcrPersistentService
import com.ea.core.JcrPersistentServiceImpl
import com.ea.core.JcrPersistentServiceImpl
import grails.util.GrailsUtil

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

        loadConfig(application.config)

        // create bean in spring context
        jcrPersistentService(JcrPersistentServiceImpl) {
            grailsApplication = application
        }
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)

        ((JcrPersistentServiceImpl) applicationContext.getBean("jcrPersistentService")).startup()
        println "JcrPersistentService startup status: Success"
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
        ((JcrPersistentServiceImpl) application.mainContext.getBean("jcrPersistentService")).shutdown()
        println "JcrPersistentService shutdown status: Success"
    }

    private ConfigObject loadConfig(config) {
        def classLoader = new GroovyClassLoader(getClass().classLoader)

        // merge plugin's config
        config.merge(new ConfigSlurper(GrailsUtil.environment).parse(classLoader.loadClass('Config')).merge(config))

        // merge plugin's cache config
        config.merge(new ConfigSlurper(GrailsUtil.environment).parse(classLoader.loadClass('GrailsJcrPluginCacheConfig')).merge(config))
    }
}
