package com.p4f.jcr

import grails.plugin.cache.Cacheable
import org.apache.commons.pool.PoolableObjectFactory
import org.apache.commons.pool.impl.GenericObjectPool
import org.apache.jackrabbit.ocm.exception.ObjectContentManagerException
import org.apache.jackrabbit.ocm.query.Query
import org.codehaus.groovy.grails.commons.GrailsApplication

/**
 * The implementation for the JCR persistent service.
 * This service will be handle pooling of the connection to JCR repository and
 * the cache mechanism to be used to cache the results.  This class is a singleton
 * as it manages the pooling and caching of all connection to JCR repository.
 */
public class JcrPersistentServiceImpl implements JcrPersistentService {
    static transactional = false

    GrailsApplication grailsApplication
    public GenericObjectPool ocmPool   // pool of object content managers
    private PoolableObjectFactory sessionFactory    // poolable object factory

    @Override
    public void startup() {
        def config = grailsApplication.config.grailsJcrPluginDataSource.properties

        // initialize the pooler containing object content manager
        sessionFactory = new DefaultPoolableOcmFactory(grailsApplication)
        ocmPool = new GenericObjectPool(
                sessionFactory,
                (Integer) config.maxActive,
                GenericObjectPool.WHEN_EXHAUSTED_BLOCK,
                0,
                (Integer) config.maxIdle);

        ocmPool.setTimeBetweenEvictionRunsMillis((Long) config.timeBetweenEvictionRunsMillis);
        ocmPool.setMinEvictableIdleTimeMillis((Long) config.minEvictableIdleTimeMillis);
        log.info "JcrPersistentService startup: success"
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

    @Cacheable(value = "jcrPojoCache", key = "{'getObject', #path}")
    @Override
    Object getObject(String path) throws ObjectContentManagerException {
        OcmWorker ocmWorker = getOcmWorker()
        try {
            return ocmWorker.getObject(path)
        } finally {
            // must return worker back to pool
            ocmPool.returnObject(ocmWorker)
        }
    }

    @Cacheable(value = "jcrPojoCache", key = "{'getObject', #path}")
    @Override
    Object getObject(Class objectClass, String path) throws ObjectContentManagerException {
        OcmWorker ocmWorker = getOcmWorker()
        try {
            return ocmWorker.getObject(objectClass, path)
        } finally {
            // must return worker back to pool
            ocmPool.returnObject(ocmWorker)
        }
    }

    @Cacheable(value = "jcrPojoCache", key = "{'getObject', #query}")
    @Override
    Object getObject(Query query) throws ObjectContentManagerException {
        OcmWorker ocmWorker = getOcmWorker()
        try {
            return ocmWorker.getObject(query)
        } finally {
            // must return worker back to pool
            ocmPool.returnObject(ocmWorker)
        }
    }

    @Cacheable(value = "jcrPojoCache", key = "{'getObjects', #query}")
    @Override
    Collection getObjects(Query query) throws ObjectContentManagerException {
        OcmWorker ocmWorker = getOcmWorker()
        try {
            return ocmWorker.getObjects(query)
        } finally {
            // must return worker back to pool
            ocmPool.returnObject(ocmWorker)
        }
    }

    @Cacheable(value = "jcrPojoCache", key = "{'getObjects', #path}")
    @Override
    Collection getObjects(Class objectClass, String path) throws ObjectContentManagerException {
        OcmWorker ocmWorker = getOcmWorker()
        try {
            return ocmWorker.getObjects(objectClass, path)
        } finally {
            // must return worker back to pool
            ocmPool.returnObject(ocmWorker)
        }
    }

    @Cacheable(value = "jcrPojoCache", key = "{#query, #language}")
    @Override
    Collection getObjects(String query, String language) {
        OcmWorker ocmWorker = getOcmWorker()
        try {
            return ocmWorker.getObjects(query, language)
        } finally {
            // must return worker back to pool
            ocmPool.returnObject(ocmWorker)
        }
    }

    /**
     * Helper method which will return OcmWorker.
     * It will also increment the borrow count on the object
     *
     * @return A OcmWorker object
     */
    private OcmWorker getOcmWorker() {
        OcmWorker ocmWorker = (OcmWorker) ocmPool.borrowObject()
        ocmWorker.incrementNumOfBorrowed()
        return ocmWorker
    }

    GrailsApplication getGrailsApplication() {
        return grailsApplication
    }

    void setGrailsApplication(GrailsApplication grailsApplication) {
        this.grailsApplication = grailsApplication
    }
}