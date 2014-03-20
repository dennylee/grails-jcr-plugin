package com.ea.core

import org.apache.commons.pool.impl.GenericObjectPool
import org.apache.jackrabbit.ocm.exception.ObjectContentManagerException
import org.apache.jackrabbit.ocm.manager.cache.ObjectCache
import org.apache.jackrabbit.ocm.query.Query

/**
 * The implementation for the persistent manager.
 * This manager will be handle pooling of the connection to JCR repository and
 * the cache mechanism to be used to cache the results.
 */
public class PersistentManagerImpl implements PersistentManager {
    // pool of object content managers
    private GenericObjectPool ocmPool

    // cache manager to be used by the object content managers
    private ObjectCache cacheManager

    public PersistentManagerImpl() {
//        ocmPool = new Base
    }

    @Override
    Object getObject(String path) throws ObjectContentManagerException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Object getObject(Class objectClass, String path) throws ObjectContentManagerException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Object getObject(Query query) throws ObjectContentManagerException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Collection getObjects(Query query) throws ObjectContentManagerException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Collection getObjects(Class objectClass, String path) throws ObjectContentManagerException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Collection getObjects(String query, String language) {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }
}