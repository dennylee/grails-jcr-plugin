package com.p4f.jcr.service

import com.p4f.jcr.OcmService
import com.p4f.jcr.OcmServiceImpl
import com.p4f.jcr.PoolableOcmServiceFactory
import com.p4f.jcr.stub.MapperStub
import com.p4f.jcr.stub.ObjectContentManagerStub
import com.p4f.jcr.stub.RepositoryStub
import org.apache.jackrabbit.ocm.mapper.Mapper

import javax.jcr.Repository
import javax.jcr.SimpleCredentials

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class PoolableOcmServiceFactoryTests {
    private PoolableOcmServiceFactory poolableOcmServiceFactory

    void setUp() {
        Repository repo = new RepositoryStub()
        SimpleCredentials creds = new SimpleCredentials("username", "password".toCharArray())
        Mapper mapper = new MapperStub()
        poolableOcmServiceFactory = new PoolableOcmServiceFactory(repo, mapper, creds, "workspace")
    }

    void "test destroyObject"() {
        OcmService ocmService = new OcmServiceImpl(new ObjectContentManagerStub())

        // successful logout
        poolableOcmServiceFactory.destroyObject(ocmService)

        // fail to logout on wrong instance
        try {
            poolableOcmServiceFactory.destroyObject(null)
            fail()
        } catch (Exception e) {
            assertTrue(true)
        }
    }

    void "test makeObject"() {
        assertTrue(poolableOcmServiceFactory.makeObject() instanceof OcmService)
    }
}
