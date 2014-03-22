package com.ea.core

import org.apache.commons.pool.BasePoolableObjectFactory
import org.apache.jackrabbit.commons.JcrUtils
import org.apache.jackrabbit.ocm.manager.impl.ObjectContentManagerImpl
import org.apache.jackrabbit.ocm.mapper.Mapper
import org.apache.jackrabbit.ocm.mapper.impl.annotation.AnnotationMapperImpl
import org.apache.jackrabbit.ocm.reflection.ReflectionUtils
import org.codehaus.groovy.grails.commons.GrailsApplication

import javax.jcr.Repository
import javax.jcr.Session
import javax.jcr.SimpleCredentials

/**
 * Default poolable factory for Object Content Managers
 */
public class DefaultPoolableOcmFactory extends BasePoolableObjectFactory {
    private GrailsApplication grailsApplication

    /**
     * Default constructor
     *
     * @param grailsApplication GrailsApplication which contains the configuration values
     */
    public DefaultPoolableOcmFactory(GrailsApplication grailsApplication) {
        this.grailsApplication = grailsApplication
    }

    @Override
    void destroyObject(Object obj) throws Exception {
        // logout and close out the connection
        ((ObjectContentManagerImpl) obj).logout()
    }

    @Override
    Object makeObject() throws Exception {
        def config = grailsApplication.config.grailsJcrPluginDataSource

        // create repo
        String host = config.host + config.path
        Repository repo = JcrUtils.getRepository(host)
        SimpleCredentials creds = new SimpleCredentials((String) config.username, ((String) config.password)?.toCharArray())

        // create session to repo
        Session session = repo.login(creds,(String) config.workspace);

        log.debug "Creating new ocm connection: host=${host}, username=${config.username}"
        println "Creating new ocm connection: host=${host}, username=${config.username}"
        return new ObjectContentManagerImpl(session, loadObjectMappings())
    }

    /**
     * Reads and loads the common object mappings and mappings made outside of this plugin
     */
    private Mapper loadObjectMappings() {
        // load plugin's common object mappings
        def classLoader = new GroovyClassLoader(getClass().classLoader)
        def config = new ConfigSlurper().parse(classLoader.loadClass('CommonObjectMapping'))

        List<Class> commonClasses = new ArrayList<Class>()
        commonClasses.addAll(config.grails.jcr.plugin.ocm.mapping.common)

        ReflectionUtils.setClassLoader(Thread.currentThread().getContextClassLoader()); // TODO: unsure why this is necessary
        Mapper mapper = new AnnotationMapperImpl(commonClasses)

        // load external object mappings
        // TODO: Load the external object mappings...how?

        return mapper
    }
}
