package com.ea.core

import org.apache.commons.pool.BasePoolableObjectFactory
import org.apache.jackrabbit.commons.JcrUtils
import org.apache.jackrabbit.ocm.manager.impl.ObjectContentManagerImpl
import org.apache.jackrabbit.ocm.mapper.DescriptorReader
import org.apache.jackrabbit.ocm.mapper.impl.annotation.AnnotationDescriptorReader
import org.apache.jackrabbit.ocm.mapper.model.MappingDescriptor

import javax.jcr.Repository
import javax.jcr.Session
import javax.jcr.SimpleCredentials

/**
 * Default poolable factory for Object Content Managers
 */
public class DefaultPoolableOcmFactory extends BasePoolableObjectFactory {
    def grailsApplication
    private MappingDescriptor mappingDescriptor

    @Override
    void destroyObject(Object obj) throws Exception {
        // logout and close out the connection
        ((ObjectContentManagerImpl) obj).logout()
    }

    @Override
    Object makeObject() throws Exception {
        Repository repo = JcrUtils.getRepository(
                (String) grailsApplication.config.grails.jcr.plugin.repo.host)

        SimpleCredentials creds = new SimpleCredentials(
                (String) grailsApplication.config.grails.jcr.plugin.repo.username,
                (String) grailsApplication.config.grails.jcr.plugin.repo.password?.toCharArray())

        Session session = repo.login(
                creds,
                (String) grailsApplication.config.grails.jcr.plugin.repo.workspace);

        // load the object mapping configuration
        loadCommonObjectMappings()
        loadExternalObjectMappings()

        // decide if we'll be using annotation or xml config files
        return new ObjectContentManagerImpl(session, mappingDescriptor.getMapper())
    }

    /**
     * Reads and loads the common object mappings
     */
    private void loadObjectMappings() {
        // load plugin's common object mappings
        ConfigSlurper configSlurper = new ConfigSlurper().parse(new File("CommonObjectMapping.groovy"))
        List<Class> commonClasses = new ArrayList<Class>()
        commonClasses.addAll(configSlurper.grails.jcr.plugin.ocm.common as List)

        DescriptorReader dr = new AnnotationDescriptorReader(commonClasses)
        mappingDescriptor = dr.loadClassDescriptors()

        // load external object mappings
        // TODO: Load the external object mappings...how?
    }
}
