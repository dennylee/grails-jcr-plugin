package com.p4f.jcr.stub

import org.apache.jackrabbit.ocm.query.Filter
import org.apache.jackrabbit.ocm.query.Query


class QueryImplStub implements Query {
    @Override
    void setFilter(Filter filter) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Filter getFilter() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void addOrderByAscending(String fieldNameAttribute) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void addOrderByDescending(String fieldNameAttribute) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
