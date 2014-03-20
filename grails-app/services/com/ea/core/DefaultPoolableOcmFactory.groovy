package com.ea.core

import org.apache.commons.pool.BasePoolableObjectFactory
import org.apache.jackrabbit.commons.JcrUtils
import org.apache.jackrabbit.ocm.manager.ObjectContentManager
import org.apache.jackrabbit.ocm.manager.impl.ObjectContentManagerImpl
import org.apache.jackrabbit.ocm.mapper.Mapper
import org.apache.jackrabbit.ocm.mapper.impl.annotation.AnnotationMapperImpl

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

    private Mapper consumeAnnotationMapper() {

    }

    public String[] consumeXmlConfigMapper() {

    }
}
