package com.p4f.jcr.service

import com.p4f.jcr.OcmServiceImpl
import com.p4f.jcr.mock.CharacterPageMock
import com.p4f.jcr.stub.ObjectContentManagerStub
import com.p4f.jcr.stub.QueryImplStub

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class OcmServiceImplTests {
    OcmServiceImpl service
    ObjectContentManagerStub ocmStub

    void setUp() {
        ocmStub = new ObjectContentManagerStub()
        service = new OcmServiceImpl(ocmStub)
    }

    void "test incrementNumOfBorrowed"() {
        service.incrementNumOfBorrowed()
        assertEquals(1, service.getNumOfTimesBorrowed())
    }

    void "test accessor methods"() {
        CharacterPageMock page = ocmStub.getCharacterPage()

        List<CharacterPageMock> c = new ArrayList<CharacterPageMock>()
        c.add(page)

        assertEquals(page, service.getObject('/content/characterPage/jcr:content'))
        assertEquals(page, service.getObject(CharacterPageMock.class, '/content/characterPage/jcr:content'))
        assertEquals(page, service.getObject(new QueryImplStub()))
        assertEquals(c, service.getObjects(new QueryImplStub()))
        assertEquals(c, service.getObjects(CharacterPageMock.class, '/content/characterPage/jcr:content'))
        assertEquals(c, service.getObjects('SELECT * FROM [cq:Page]', 'JCR-SQL2'))
        assertFalse(service.getId() == null)
    }
}
