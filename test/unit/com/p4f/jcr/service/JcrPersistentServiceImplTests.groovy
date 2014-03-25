package com.p4f.jcr.service

import com.p4f.jcr.JcrPersistentService
import com.p4f.jcr.JcrPersistentServiceImpl
import com.p4f.jcr.mock.CharacterPageMock
import com.p4f.jcr.stub.ObjectContentManagerStub
import com.p4f.jcr.stub.PoolableOcmServiceFactoryStub
import com.p4f.jcr.stub.QueryImplStub
import org.apache.commons.pool.impl.GenericObjectPool

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*


/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class JcrPersistentServiceImplTests {
    JcrPersistentService service

    void setUp() {
        GenericObjectPool objectPool = new GenericObjectPool(new PoolableOcmServiceFactoryStub())
        service = new JcrPersistentServiceImpl(objectPool)
    }

    void "test shutdown"() {
        assertTrue(service.shutdown() )
    }

    void "test getObject method calls"() {
        ObjectContentManagerStub ocmStub = new ObjectContentManagerStub()
        CharacterPageMock page = ocmStub.getCharacterPage()

        List<CharacterPageMock> c = new ArrayList<CharacterPageMock>()
        c.add(page)

        assertEquals(page, service.getObject('/content/characterPage/jcr:content'))
        assertEquals(page, service.getObject(CharacterPageMock.class, '/content/characterPage/jcr:content'))
        assertEquals(page, service.getObject(new QueryImplStub()))
        assertEquals(c, service.getObjects(new QueryImplStub()))
        assertEquals(c, service.getObjects(CharacterPageMock.class, '/content/characterPage/jcr:content'))
        assertEquals(c, service.getObjects('SELECT * FROM [cq:Page]', 'JCR-SQL2'))
    }
}
