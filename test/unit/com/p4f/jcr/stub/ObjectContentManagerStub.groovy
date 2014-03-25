package com.p4f.jcr.stub

import com.p4f.jcr.components.Text
import com.p4f.jcr.mock.CharacterPageMock
import org.apache.jackrabbit.ocm.exception.IllegalUnlockException
import org.apache.jackrabbit.ocm.exception.LockedException
import org.apache.jackrabbit.ocm.exception.ObjectContentManagerException
import org.apache.jackrabbit.ocm.lock.Lock
import org.apache.jackrabbit.ocm.manager.ObjectContentManager
import org.apache.jackrabbit.ocm.query.Query
import org.apache.jackrabbit.ocm.query.QueryManager
import org.apache.jackrabbit.ocm.version.Version
import org.apache.jackrabbit.ocm.version.VersionIterator

import javax.jcr.Session
import javax.jcr.version.VersionException

/**
 * Stub for the ObjectContentManager
 */
class ObjectContentManagerStub implements ObjectContentManager {
    private CharacterPageMock characterPage
    private Text text

    public ObjectContentManagerStub() {
        text = new Text()
        text.setId(200)
        text.setText('text')
        text.setPath('/component')

        characterPage = new CharacterPageMock()
        characterPage.setId(100)
        characterPage.setJcrTitle('jcrTitle')
        characterPage.setJcrCreatedBy('jcrCreatedBy')
        characterPage.setPath('/content/characterPage/jcr:content')
        characterPage.setVersion(100)
    }

    @Override
    boolean objectExists(String path) throws ObjectContentManagerException {
        return false  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    boolean isPersistent(Class clazz) {
        return false  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void insert(Object object) throws ObjectContentManagerException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void update(Object object) throws ObjectContentManagerException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Object getObject(String path) throws ObjectContentManagerException {
        return path == characterPage.getPath() ? characterPage : null
    }

    @Override
    Object getObjectByUuid(String uuid) throws ObjectContentManagerException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Object getObject(String path, String versionNumber) throws ObjectContentManagerException {
        return path == characterPage.getPath() && versionNumber == characterPage.getVersion() ? characterPage : null
    }

    @Override
    Object getObject(Class objectClass, String path) throws ObjectContentManagerException {
        return objectClass == CharacterPageMock.class && path == characterPage.getPath() ? characterPage : null
    }

    @Override
    Object getObject(Class objectClass, String path, String versionNumber) throws ObjectContentManagerException {
        return objectClass == CharacterPageMock.class && path == characterPage.getPath() && versionNumber == characterPage.getVersion() ? characterPage : null
    }

    @Override
    void retrieveMappedAttribute(Object object, String attributeName) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void retrieveAllMappedAttributes(Object object) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void remove(String path) throws ObjectContentManagerException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void remove(Object object) throws ObjectContentManagerException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void remove(Query query) throws ObjectContentManagerException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Object getObject(Query query) throws ObjectContentManagerException {
        return query instanceof QueryImplStub ? characterPage : null
    }

    @Override
    Collection getObjects(Query query) throws ObjectContentManagerException {
        List<CharacterPageMock> c = new ArrayList<CharacterPageMock>()
        c.add(characterPage)
        return query instanceof QueryImplStub ? c : null
    }

    @Override
    Collection getObjects(Class objectClass, String path) throws ObjectContentManagerException {
        List<CharacterPageMock> c = new ArrayList<CharacterPageMock>()
        c.add(characterPage)
        return objectClass == CharacterPageMock.class && path == characterPage.getPath() ? c : null
    }

    @Override
    Collection getObjects(String query, String language) {
        List<CharacterPageMock> c = new ArrayList<CharacterPageMock>()
        c.add(characterPage)
        return query == 'SELECT * FROM [cq:Page]' && language == 'JCR-SQL2' ? c : null
    }

    @Override
    Iterator getObjectIterator(Query query) throws ObjectContentManagerException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Iterator getObjectIterator(String query, String language) {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void checkout(String path) throws VersionException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void checkin(String path) throws VersionException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void checkin(String path, String[] versionLabels) throws VersionException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    String[] getVersionLabels(String path, String versionName) throws VersionException {
        return new String[0]  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    String[] getAllVersionLabels(String path) throws VersionException {
        return new String[0]  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void addVersionLabel(String path, String versionName, String versionLabel) throws VersionException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    VersionIterator getAllVersions(String path) throws VersionException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Version getRootVersion(String path) throws VersionException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Version getBaseVersion(String path) throws VersionException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Version getVersion(String path, String versionName) throws VersionException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void save() throws ObjectContentManagerException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void logout() throws ObjectContentManagerException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Lock lock(String path, boolean isDeep, boolean isSessionScoped) throws LockedException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void unlock(String path, String lockToken) throws IllegalUnlockException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    boolean isLocked(String absPath) {
        return false  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    QueryManager getQueryManager() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void refresh(boolean keepChanges) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void move(String srcPath, String destPath) throws ObjectContentManagerException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void copy(String srcPath, String destPath) throws ObjectContentManagerException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Session getSession() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    public CharacterPageMock getCharacterPage() {
        return characterPage
    }
}
