package com.p4f.jcr.stub

import org.apache.jackrabbit.ocm.mapper.Mapper
import org.apache.jackrabbit.ocm.mapper.model.ClassDescriptor

class MapperStub implements Mapper {
    @Override
    ClassDescriptor getClassDescriptorByClass(Class clazz) {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    ClassDescriptor getClassDescriptorByNodeType(String jcrNodeType) {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }
}
