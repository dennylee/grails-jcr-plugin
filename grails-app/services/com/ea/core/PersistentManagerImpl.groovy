package com.ea.core

import org.apache.commons.pool.PoolableObjectFactory
import org.apache.commons.pool.impl.GenericObjectPool
import org.apache.jackrabbit.ocm.exception.ObjectContentManagerException
import org.apache.jackrabbit.ocm.query.Query
import org.apache.jackrabbit.ocm.manager.ObjectContentManager
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.springframework.beans.factory.InitializingBean

/**
 * The implementation for the persistent manager.
 * This manager will be handle pooling of the connection to JCR repository and
 * the cache mechanism to be used to cache the results.  This class is a singleton
 * as it manages the pooling and caching of all connection to JCR repository.
 */
public class PersistentManagerImpl implements PersistentManager {
    static transactional = false
    GrailsApplication grailsApplication

    // pool of object content managers
    private GenericObjectPool ocmPool

    // poolable object factory
    private PoolableObjectFactory sessionFactory

    private Map configs = new HashMap<String, String>()

    public PersistentManagerImpl() {
//        this.grailsApplication = grailsApplication
//        sessionFactory = new DefaultPoolableOcmFactory()
        def config = 'grailsApplication.config.grails.jcr.plugin.ocm.pool'
//        ocmPool = GenericObjectPool(sessionFactory ,0, GenericObjectPool.WHEN_EXHAUSTED_GROW, 0, 5);
    }

    public PersistentManagerImpl(GrailsApplication grailsApplication) {
        this.grailsApplication = grailsApplication
    }

    @Override
    public void init() {
        def config = grailsApplication.config.grails.jcr.plugin.ocm.pool

        sessionFactory = new DefaultPoolableOcmFactory(grailsApplication)
        ocmPool = new GenericObjectPool(sessionFactory ,0, GenericObjectPool.WHEN_EXHAUSTED_GROW, 0, 5);

        ocmPool.setTimeBetweenEvictionRunsMillis((Long) config.timeBetweenEvictionRuns);
        ocmPool.setMinEvictableIdleTimeMillis((Long) config.timeBetweenEvictionRuns);
    }

    @Override
    public void destroy() {
        try {
            ocmPool?.close()
        } catch (Exception e) {
            throw new RuntimeException("Unable to destroy: ${e.getMessage()}", e)
        }
    }

    // TODO: Cache the getObject calls...maybe use @Cacheable
    @Override
    Object getObject(String path) throws ObjectContentManagerException {
        return getOcmFromPool().getObject(path)
    }

    @Override
    Object getObject(Class objectClass, String path) throws ObjectContentManagerException {
        return getOcmFromPool().getObject(objectClass, path)
    }

    @Override
    Object getObject(Query query) throws ObjectContentManagerException {
        return getOcmFromPool().getObject(query)
    }

    @Override
    Collection getObjects(Query query) throws ObjectContentManagerException {
        return getOcmFromPool().getObjects(query)
    }

    @Override
    Collection getObjects(Class objectClass, String path) throws ObjectContentManagerException {
        return getOcmFromPool().getObjects(objectClass, path)
    }

    @Override
    Collection getObjects(String query, String language) {
        return getOcmFromPool().getObjects(query, language)
    }

    private ObjectContentManager getOcmFromPool() {
        return (ObjectContentManager) ocmPool.borrowObject()
    }

    GrailsApplication getGrailsApplication() {
        return grailsApplication
    }

    void setGrailsApplication(GrailsApplication grailsApplication) {
        this.grailsApplication = grailsApplication
    }
}