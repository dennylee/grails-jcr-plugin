package com.ea.core

import org.apache.jackrabbit.ocm.manager.impl.ObjectContentManagerImpl
import org.apache.jackrabbit.ocm.mapper.Mapper

import javax.jcr.Session
import java.text.SimpleDateFormat

/**
 * A Object Content Manager worker.
 * It doesn't do anything other than contains additional information to help facilitate
 * more information on the workers that talks to JCR repository.  Basically, a shell
 * over the ObjectContentManager.
 */
public class OcmWorker extends ObjectContentManagerImpl {

    private UUID id
    private Date created
    private long numOfTimesBorrowed

    /**
     *  Creates a new <code>ObjectContentManager</code> that uses the passed in
     *  <code>Mapper</code>, and a <code>Session</code>
     *
     * @param mapper
     *             the Mapper component
     * @param session
     *             The JCR session
     */
    public OcmWorker(Session session, Mapper mapper) {
        super(session, mapper)
        id = UUID.randomUUID()
        created = new Date(System.currentTimeMillis())
    }

    public void incrementNumOfBorrowed() {
        numOfTimesBorrowed++
    }

    UUID getId() {
        return id
    }

    Date getCreated() {
        return created
    }

    long getNumOfTimesBorrowed() {
        return numOfTimesBorrowed
    }

    @Override
    String toString() {
        return "${id}: Created=${(new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z")).format(created)}, numOfTimesBorrowed=${numOfTimesBorrowed}"
    }
}
