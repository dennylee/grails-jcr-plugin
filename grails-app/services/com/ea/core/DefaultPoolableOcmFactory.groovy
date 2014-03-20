package com.ea.core

import org.apache.commons.pool.BasePoolableObjectFactory
import org.apache.jackrabbit.commons.JcrUtils
import org.apache.jackrabbit.ocm.manager.ObjectContentManager
import org.apache.jackrabbit.ocm.manager.impl.ObjectContentManagerImpl
import org.apache.jackrabbit.ocm.mapper.Mapper
import org.apache.jackrabbit.ocm.mapper.impl.annotation.AnnotationDescriptorReader
import org.apache.jackrabbit.ocm.mapper.impl.annotation.AnnotationMapperImpl
import org.apache.jackrabbit.ocm.mapper.impl.digester.DigesterDescriptorReader
import org.apache.jackrabbit.ocm.mapper.impl.digester.DigesterMapperImpl

import javax.jcr.Repository
import javax.jcr.Session
import javax.jcr.SimpleCredentials

/**
 * Default poolable factory for Object Content Managers
 */
public class DefaultPoolableOcmFactory extends BasePoolableObjectFactory {
    def grailsApplication
    private ObjectContentManager ocm

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

        // decide if we'll be using annotation or xml config files
        ObjectContentManager ocm
        if ("xml".equalsIgnoreCase((String) grailsApplication.config.grails.jcr.plugin.ocm.strategy)) {
            ocm =  new ObjectContentManagerImpl(session, consumeAnnotationMapper());

        } else {
            ocm =  new ObjectContentManagerImpl(session, consumeXmlConfigMapper());
        }

        return ocm
    }

    /**
     * Load the object mapping configuration.
     * This method will merge the XML configuration into annotated
     * object if the strategy is "xml" since the object content manager
     * is expecting it in Mapper.
     *
     * @return A Mapper containing all the mapped objects
     */
    private Mapper loadObjectMappings() {
        // first load the plugin's object mapping, which will be using
        // annotation to map the objects
        Mapper pluginsObjectMapping = AnnotationDescriptorReader()




        Mapper mapper
        String[] mapping = grailsApplication.config.grails.jcr.plugin.ocm.mapping
        if ("xml".equalsIgnoreCase((String) grailsApplication.config.grails.jcr.plugin.ocm.strategy)) {
            mapper = new DigesterDescriptorReader(mapping)
        } else {
            mapper = new AnnotationDescriptorReader(mapping)
        }



        return mapper
    }

}
