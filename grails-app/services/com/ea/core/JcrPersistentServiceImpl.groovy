package com.ea.core

import grails.plugin.cache.Cacheable
import org.apache.commons.pool.PoolableObjectFactory
import org.apache.commons.pool.impl.GenericObjectPool
import org.apache.jackrabbit.ocm.exception.ObjectContentManagerException
import org.apache.jackrabbit.ocm.query.Query
import org.apache.jackrabbit.ocm.manager.ObjectContentManager
import org.codehaus.groovy.grails.commons.GrailsApplication

/**
 * The implementation for the JCR persistent service.
 * This service will be handle pooling of the connection to JCR repository and
 * the cache mechanism to be used to cache the results.  This class is a singleton
 * as it manages the pooling and caching of all connection to JCR repository.
 */
public class JcrPersistentServiceImpl implements JcrPersistentService {
    static transactional = false
    public int x = 0
    private Object obj

    GrailsApplication grailsApplication
    private GenericObjectPool ocmPool   // pool of object content managers
    private PoolableObjectFactory sessionFactory    // poolable object factory

//    @Override
    public void startup() {
        def config = grailsApplication.config.grailsJcrPluginDataSource.properties

        // initialize the pooler containing object content manager
        sessionFactory = new DefaultPoolableOcmFactory(grailsApplication)
        ocmPool = new GenericObjectPool(
                sessionFactory,
                (Integer) config.maxActive,
                GenericObjectPool.WHEN_EXHAUSTED_GROW,
                0,
                (Integer) config.maxIdle);

        ocmPool.setTimeBetweenEvictionRunsMillis((Long) config.timeBetweenEvictionRunsMillis);
        ocmPool.setMinEvictableIdleTimeMillis((Long) config.minEvictableIdleTimeMillis);
        log.info "JcrPersistentService startup: success"
        println "startup!!"
    }

    @Override
    public void shutdown() {
        try {
            ocmPool?.close()
            log.info "JcrPersistentService shutdown: success"
        } catch (Exception e) {
            log.error "JcrPersistentService shutdown: failed - ${e.getMessage()}"
        }
    }

    @Cacheable(value = "jcrPojoCache", key = "#path")
    @Override
    Object getObject(String path) throws ObjectContentManagerException {
        synchronized (this) {
            println "${x++} - instance = ${this}"
        }

        ObjectContentManager ocm = getOcmFromPool()
        Object o = ocm.getObject(path)
        ocmPool.returnObject(ocm)

        return o
    }

    @Cacheable(value = "jcrPojoCache", key = "{'getObject', #objectClass, #path}")
    @Override
    Object getObject(Class objectClass, String path) throws ObjectContentManagerException {
        return getOcmFromPool().getObject(objectClass, path)
    }

    @Cacheable(value = "jcrPojoCache", key = "{'getObject', #query}")
    @Override
    Object getObject(Query query) throws ObjectContentManagerException {
        return getOcmFromPool().getObject(query)
    }

    @Cacheable(value = "jcrPojoCache", key = "{'getObjects', #query}")
    @Override
    Collection getObjects(Query query) throws ObjectContentManagerException {
        return getOcmFromPool().getObjects(query)
    }

    @Cacheable(value = "jcrPojoCache", key = "{'getObjects', #path}")
    @Override
    Collection getObjects(Class objectClass, String path) throws ObjectContentManagerException {
        return getOcmFromPool().getObjects(objectClass, path)
    }

    @Cacheable(value = "jcrPojoCache", key = "{'getObjects', #path}")
    @Override
    Collection getObjects(String query, String language) {
        return getOcmFromPool().getObjects(query, language)
    }

    /**
     * Helper method which will return as ObjectContentManager by casting
     *
     * @return A ObjectContentManager
     */
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