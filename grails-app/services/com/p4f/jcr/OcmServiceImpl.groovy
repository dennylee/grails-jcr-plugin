package com.p4f.jcr

import org.apache.jackrabbit.ocm.exception.ObjectContentManagerException
import org.apache.jackrabbit.ocm.manager.ObjectContentManager
import org.apache.jackrabbit.ocm.query.Query

import java.text.SimpleDateFormat

/**
 * Wrapper class of the ObjectContentManager.
 * This wrapper class help ease the testing and to have the ObjectContentManager
 * to be injected as a delegate.
 */
class OcmServiceImpl implements OcmService {
    static transactional = false

    private UUID id
    private Date createdDate
    private long numOfTimesBorrowed
    private ObjectContentManager ocm

    /**
     *  Creates a new <code>ObjectContentManager</code> that uses the passed in
     *  <code>Mapper</code>, and a <code>Session</code>
     *
     * @param mapper
     *             the Mapper component
     * @param session
     *             The JCR session
     */
    public OcmServiceImpl(ObjectContentManager ocm) {
        id = UUID.randomUUID()
        createdDate = new Date(System.currentTimeMillis())
        this.ocm = ocm
    }

    @Override
    Object getObject(String path) throws ObjectContentManagerException {
        return ocm.getObject(path)
    }

    @Override
    Object getObject(Class objectClass, String path) throws ObjectContentManagerException {
        return ocm.getObject(objectClass, path)
    }

    @Override
    Object getObject(Query query) throws ObjectContentManagerException {
        return ocm.getObject(query)
    }

    @Override
    Collection getObjects(Query query) throws ObjectContentManagerException {
        return ocm.getObjects(query)
    }

    @Override
    Collection getObjects(Class objectClass, String path) throws ObjectContentManagerException {
        return ocm.getObjects(objectClass, path)
    }

    @Override
    Collection getObjects(String query, String language) {
        return ocm.getObjects(query, language)
    }

    @Override
    void logout() throws ObjectContentManagerException {
        ocm.logout()
    }

    public void incrementNumOfBorrowed() {
        numOfTimesBorrowed++
    }

    long getNumOfTimesBorrowed() {
        return numOfTimesBorrowed
    }

    UUID getId() {
        return id
    }

    @Override
    String toString() {
        return "${id}: Created=${(new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z")).format(createdDate)}, numOfTimesBorrowed=${numOfTimesBorrowed}"
    }
}

