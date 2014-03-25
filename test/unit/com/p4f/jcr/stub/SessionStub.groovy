package com.p4f.jcr.stub

import org.xml.sax.SAXException

import javax.jcr.AccessDeniedException
import javax.jcr.Credentials
import javax.jcr.InvalidItemStateException
import javax.jcr.InvalidSerializedDataException
import javax.jcr.Item
import javax.jcr.ItemExistsException
import javax.jcr.ItemNotFoundException
import javax.jcr.LoginException
import javax.jcr.NamespaceException
import javax.jcr.PathNotFoundException
import javax.jcr.Property
import javax.jcr.ReferentialIntegrityException
import javax.jcr.Repository
import javax.jcr.RepositoryException
import javax.jcr.Session
import javax.jcr.UnsupportedRepositoryOperationException
import javax.jcr.ValueFactory
import javax.jcr.Workspace
import javax.jcr.lock.LockException
import javax.jcr.nodetype.ConstraintViolationException
import javax.jcr.nodetype.NoSuchNodeTypeException
import javax.jcr.retention.RetentionManager
import javax.jcr.security.AccessControlManager
import javax.jcr.version.VersionException
import java.security.AccessControlException


class SessionStub implements Session {

    @Override
    Repository getRepository() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    String getUserID() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    String[] getAttributeNames() {
        return new String[0]  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Object getAttribute(String name) {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Workspace getWorkspace() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    javax.jcr.Node getRootNode() throws RepositoryException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Session impersonate(Credentials credentials) throws LoginException, RepositoryException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    javax.jcr.Node getNodeByUUID(String uuid) throws ItemNotFoundException, RepositoryException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    javax.jcr.Node getNodeByIdentifier(String id) throws ItemNotFoundException, RepositoryException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Item getItem(String absPath) throws PathNotFoundException, RepositoryException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    javax.jcr.Node getNode(String absPath) throws PathNotFoundException, RepositoryException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    boolean itemExists(String absPath) throws RepositoryException {
        return false  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    boolean nodeExists(String absPath) throws RepositoryException {
        return false  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    boolean propertyExists(String absPath) throws RepositoryException {
        return false  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void move(String srcAbsPath, String destAbsPath) throws ItemExistsException, PathNotFoundException, VersionException, ConstraintViolationException, LockException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void removeItem(String absPath) throws VersionException, LockException, ConstraintViolationException, AccessDeniedException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void save() throws AccessDeniedException, ItemExistsException, ReferentialIntegrityException, ConstraintViolationException, InvalidItemStateException, VersionException, LockException, NoSuchNodeTypeException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void refresh(boolean keepChanges) throws RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    boolean hasPendingChanges() throws RepositoryException {
        return false  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    ValueFactory getValueFactory() throws UnsupportedRepositoryOperationException, RepositoryException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    boolean hasPermission(String absPath, String actions) throws RepositoryException {
        return false  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void checkPermission(String absPath, String actions) throws AccessControlException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    boolean hasCapability(String methodName, Object target, Object[] arguments) throws RepositoryException {
        return false  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    org.xml.sax.ContentHandler getImportContentHandler(String parentAbsPath, int uuidBehavior) throws PathNotFoundException, ConstraintViolationException, VersionException, LockException, RepositoryException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void importXML(String parentAbsPath, InputStream inStream, int uuidBehavior) throws IOException, PathNotFoundException, ItemExistsException, ConstraintViolationException, VersionException, InvalidSerializedDataException, LockException, RepositoryException {

    }

    @Override
    void exportSystemView(String absPath, org.xml.sax.ContentHandler contentHandler, boolean skipBinary, boolean noRecurse) throws PathNotFoundException, SAXException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void exportSystemView(String absPath, OutputStream out, boolean skipBinary, boolean noRecurse) throws IOException, PathNotFoundException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void exportDocumentView(String absPath, org.xml.sax.ContentHandler contentHandler, boolean skipBinary, boolean noRecurse) throws PathNotFoundException, SAXException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void exportDocumentView(String absPath, OutputStream out, boolean skipBinary, boolean noRecurse) throws IOException, PathNotFoundException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void setNamespacePrefix(String prefix, String uri) throws NamespaceException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    String[] getNamespacePrefixes() throws RepositoryException {
        return new String[0]  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    String getNamespaceURI(String prefix) throws NamespaceException, RepositoryException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    String getNamespacePrefix(String uri) throws NamespaceException, RepositoryException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void logout() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    boolean isLive() {
        return false  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void addLockToken(String lt) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    String[] getLockTokens() {
        return new String[0]  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void removeLockToken(String lt) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    AccessControlManager getAccessControlManager() throws UnsupportedRepositoryOperationException, RepositoryException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    RetentionManager getRetentionManager() throws UnsupportedRepositoryOperationException, RepositoryException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property getProperty(String absPath) throws PathNotFoundException, RepositoryException {
        return null
    }
}
