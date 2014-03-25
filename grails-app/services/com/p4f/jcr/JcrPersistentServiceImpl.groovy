package com.p4f.jcr

import grails.plugin.cache.Cacheable
import org.apache.commons.pool.impl.GenericObjectPool
import org.apache.jackrabbit.ocm.exception.ObjectContentManagerException
import org.apache.jackrabbit.ocm.query.Query

/**
 * The implementation for the JCR persistent service.
 * This service will be handle pooling of the connection to JCR repository and
 * the cache mechanism to be used to cache the results.  This class is a singleton
 * as it manages the pooling and caching of all connection to JCR repository.
 */
public class JcrPersistentServiceImpl implements JcrPersistentService {
    static transactional = false

    private GenericObjectPool ocmPool   // pool of object content managers

    public JcrPersistentServiceImpl() {
    }

    public JcrPersistentServiceImpl(GenericObjectPool objectPool) {
        ocmPool = objectPool
    }

    @Override
    public boolean shutdown() {
        ocmPool?.close()
        return true
    }

    @Cacheable(value = "jcrPojoCache", key = "{'getObject', #path}")
    @Override
    Object getObject(String path) throws ObjectContentManagerException {
        OcmService ocmService = getOcmService()
        try {
            return ocmService.getObject(path)
        } finally {
            // must return worker back to pool
            ocmPool.returnObject(ocmService)
        }
    }

    @Cacheable(value = "jcrPojoCache", key = "{'getObject', #path}")
    @Override
    Object getObject(Class objectClass, String path) throws ObjectContentManagerException {
        OcmService ocmService = getOcmService()
        try {
            return ocmService.getObject(objectClass, path)
        } finally {
            // must return worker back to pool
            ocmPool.returnObject(ocmService)
        }
    }

    @Cacheable(value = "jcrPojoCache", key = "{'getObject', #query}")
    @Override
    Object getObject(Query query) throws ObjectContentManagerException {
        OcmService ocmService = getOcmService()
        try {
            return ocmService.getObject(query)
        } finally {
            // must return worker back to pool
            ocmPool.returnObject(ocmService)
        }
    }

    @Cacheable(value = "jcrPojoCache", key = "{'getObjects', #query}")
    @Override
    Collection getObjects(Query query) throws ObjectContentManagerException {
        OcmService ocmService = getOcmService()
        try {
            return ocmService.getObjects(query)
        } finally {
            // must return worker back to pool
            ocmPool.returnObject(ocmService)
        }
    }

    @Cacheable(value = "jcrPojoCache", key = "{'getObjects', #path}")
    @Override
    Collection getObjects(Class objectClass, String path) throws ObjectContentManagerException {
        OcmService ocmService = getOcmService()
        try {
            return ocmService.getObjects(objectClass, path)
        } finally {
            // must return worker back to pool
            ocmPool.returnObject(ocmService)
        }
    }

    @Cacheable(value = "jcrPojoCache", key = "{#query, #language}")
    @Override
    Collection getObjects(String query, String language) {
        OcmService ocmService = getOcmService()
        try {
            return ocmService.getObjects(query, language)
        } finally {
            // must return worker back to pool
            ocmPool.returnObject(ocmService)
        }
    }

    /**
     * Helper method which will return OcmService.
     * It will also increment the borrow count on the object
     *
     * @return A OcmService object
     */
    protected OcmService getOcmService() {
        OcmServiceImpl ocmService = (OcmServiceImpl) ocmPool.borrowObject()
        ocmService.incrementNumOfBorrowed()
        return ocmService
    }
}