package com.p4f.jcr.stub

import com.p4f.jcr.OcmServiceImpl
import org.apache.commons.pool.BasePoolableObjectFactory

class PoolableOcmServiceFactoryStub extends BasePoolableObjectFactory {

    @Override
    Object makeObject() throws Exception {
        return new OcmServiceImpl(new ObjectContentManagerStub())
    }
}
