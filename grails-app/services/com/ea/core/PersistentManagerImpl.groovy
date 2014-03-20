package com.ea.core

import org.apache.commons.pool.PoolableObjectFactory
import org.apache.commons.pool.impl.GenericObjectPool
import org.apache.jackrabbit.ocm.exception.ObjectContentManagerException
import org.apache.jackrabbit.ocm.query.Query
import org.apache.jackrabbit.ocm.manager.ObjectContentManager

/**
 * The implementation for the persistent manager.
 * This manager will be handle pooling of the connection to JCR repository and
 * the cache mechanism to be used to cache the results.
 */
public class PersistentManagerImpl implements PersistentManager {
    def grailsApplication

    // pool of object content managers
    private GenericObjectPool ocmPool

    // poolable object factory
    private PoolableObjectFactory sessionFactory

    public PersistentManagerImpl() {
        sessionFactory = new DefaultPoolableOcmFactory()
        ocmPool = GenericObjectPool(sessionFactory ,0, GenericObjectPool.WHEN_EXHAUSTED_GROW, 0, 5);
    }

    public PersistentManagerImpl(PoolableObjectFactory sessionFactory, GenericObjectPool ocmPool) {
        this.sessionFactory = sessionFactory
        this.ocmFromPool = ocmFromPool
    }

    @Override
    void init() {
        ocmPool.setTimeBetweenEvictionRunsMillis(60000);
        ocmPool.setMinEvictableIdleTimeMillis(300000);
    }

    @Override
    void destory() {
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
}