package com.p4f.jcr

import org.apache.commons.pool.BasePoolableObjectFactory
import org.apache.jackrabbit.ocm.manager.ObjectContentManager
import org.apache.jackrabbit.ocm.manager.impl.ObjectContentManagerImpl
import org.apache.jackrabbit.ocm.mapper.Mapper

import javax.jcr.Repository
import javax.jcr.Session
import javax.jcr.SimpleCredentials

/**
 * Default poolable factory for Object Content Managers
 */
public class PoolableOcmServiceFactory extends BasePoolableObjectFactory {
    private Repository repository
    private SimpleCredentials simpleCredentials
    private String workspace
    private Mapper mapper

    public PoolableOcmServiceFactory(Repository repository, Mapper mapper, SimpleCredentials simpleCredentials, String workspace) {
        this.repository = repository
        this.simpleCredentials = simpleCredentials
        this.workspace = workspace
        this.mapper = mapper
    }

    @Override
    void destroyObject(Object obj) throws Exception {
        // logout and close out the connection
        if (obj instanceof OcmService) {
            OcmServiceImpl ocmService = (OcmServiceImpl) obj
            ocmService.logout()
            log.debug "Poolable Factory to destroyed OcmService ${ocmService.getId()}."
        } else {
            log.error "Object not instance of OcmService to destroy: ${obj}"
            throw new Exception("Object not instance of OcmService to destroy: ${obj}")
        }
    }

    @Override
    Object makeObject() throws Exception {
        Session session = repository.login(simpleCredentials, workspace)
        ObjectContentManager ocm = new ObjectContentManagerImpl(session, mapper)
        OcmServiceImpl ocmService = new OcmServiceImpl(ocm)

        log.debug "Poolable factory to make OcmService ${ocmService.getId()}"

        return ocmService
    }


}
