package com.ea.core

import org.apache.commons.pool.BasePoolableObjectFactory
import org.apache.jackrabbit.commons.JcrUtils
import org.apache.jackrabbit.ocm.manager.impl.ObjectContentManagerImpl
import org.apache.jackrabbit.ocm.mapper.DescriptorReader
import org.apache.jackrabbit.ocm.mapper.Mapper
import org.apache.jackrabbit.ocm.mapper.impl.annotation.AnnotationDescriptorReader
import org.apache.jackrabbit.ocm.mapper.impl.annotation.AnnotationMapperImpl
import org.apache.jackrabbit.ocm.mapper.model.MappingDescriptor
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
    private Mapper mapper

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
        def config = grailsApplication.config.grails.jcr.plugin.repo
        Repository repo = JcrUtils.getRepository(config.host + config.path)

        SimpleCredentials creds = new SimpleCredentials((String) config.username, ((String) config.password)?.toCharArray())

        Session session = repo.login(creds,(String) config.workspace);

        // load the object mapping configuration
        loadObjectMappings()
//        loadExternalObjectMappings()

        // decide if we'll be using annotation or xml config files
        return new ObjectContentManagerImpl(session, mapper)
    }

    /**
     * Reads and loads the common object mappings
     */
    private void loadObjectMappings() {
        // load plugin's common object mappings
        ConfigObject configSlurper = new ConfigSlurper().parse(new File("grails-app/conf/CommonObjectMapping.groovy").toURL())
        List<Class> commonClasses = new ArrayList<Class>()
        commonClasses.addAll(configSlurper.grails.jcr.plugin.ocm.mapping.common as List)

//        commonClasses.add(com.ea.core.JcrNode.class)
        ReflectionUtils.setClassLoader(Thread.currentThread().getContextClassLoader());
        mapper = new AnnotationMapperImpl(commonClasses)



        // load external object mappings
        // TODO: Load the external object mappings...how?
    }
}
