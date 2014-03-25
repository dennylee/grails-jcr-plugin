package com.p4f.jcr.stub

import javax.jcr.Credentials
import javax.jcr.LoginException
import javax.jcr.NoSuchWorkspaceException
import javax.jcr.Repository
import javax.jcr.RepositoryException
import javax.jcr.Session
import javax.jcr.Value

class RepositoryStub implements Repository {
    @Override
    String[] getDescriptorKeys() {
        return new String[0]  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    boolean isStandardDescriptor(String key) {
        return false  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    boolean isSingleValueDescriptor(String key) {
        return false  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Value getDescriptorValue(String key) {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Value[] getDescriptorValues(String key) {
        return new Value[0]  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    String getDescriptor(String key) {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Session login(Credentials credentials, String workspaceName) throws LoginException, NoSuchWorkspaceException, RepositoryException {
        return new SessionStub()
    }

    @Override
    Session login(Credentials credentials) throws LoginException, RepositoryException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Session login(String workspaceName) throws LoginException, NoSuchWorkspaceException, RepositoryException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Session login() throws LoginException, RepositoryException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }
}
