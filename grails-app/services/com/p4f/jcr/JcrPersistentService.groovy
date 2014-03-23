package com.p4f.jcr

import org.apache.jackrabbit.ocm.exception.ObjectContentManagerException
import org.apache.jackrabbit.ocm.query.Query

/**
 * Defines the methods that could communicate with the JCR repository.
 * Most of the methods are 'Get' calls because the plugin is designed
 * to only retrieve data from CQ5, not update nor persist them.
 */
public interface JcrPersistentService {

    /**
     * Starts up the service
     */
    public void startup()
    
    /**
     * Shutsdown the service
     */
    public void shutdown()

    /**
     * Get an object from the JCR repository
     *
     * @param path
     *            the object path
     * @return the object found or null
     *
     * @throws ObjectContentManagerException
     *             when it is not possible to retrieve the object
     */
    public Object getObject(String path) throws ObjectContentManagerException

    /**
     * Get an object from the JCR repository
     *
     * @param objectClass
     *            the object class
     * @param path
     *            the object path
     * @return the object found or null
     *
     * @throws ObjectContentManagerException
     *             when it is not possible to retrieve the object
     */
    public Object getObject(Class objectClass, String path) throws ObjectContentManagerException

    /**
     * Retrieve an object matching to a query
     *
     * @param query
     *            The Query object used to seach the object
     * @return The object found or null
     * @throws ObjectContentManagerException
     *             when it is not possible to retrieve the object
     *
     */
    public Object getObject(Query query) throws ObjectContentManagerException

    /**
     * Retrieve some objects matching to a query
     *
     * @param query
     *            The query used to seach the objects
     * @return a collection of objects found
     * @throws ObjectContentManagerException
     *             when it is not possible to retrieve the objects
     *
     */
    public Collection getObjects(Query query) throws ObjectContentManagerException

    /**
     * Returns a list of objects of that particular class which are associated to a specific path.
     * This method is helpfull when same name sibling is used to create nodes.
     * This would not return the objects anywhere below the denoted path.
     *
     * @param objectClass
     * @param path Node path.
     * @return a collection of object found
     */

    public Collection getObjects(Class objectClass, String path) throws ObjectContentManagerException

    /**
     * Return a list of object matching to a JCR query
     *
     * @param query the JCR query
     * @param language the JCR Language ("XPATH" or "SQL").
     * @return
     */
    public Collection getObjects(String query, String language)
}
