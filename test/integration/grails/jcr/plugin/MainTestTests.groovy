package grails.jcr.plugin

import com.ea.core.JcrNode
import com.ea.core.PersistentManager
import com.ea.core.components.Header
import com.ea.core.components.Text
import com.ea.core.page.CharacterPage
import com.ea.core.pageContent.PageContent
import org.apache.jackrabbit.commons.JcrUtils
import org.apache.jackrabbit.ocm.manager.ObjectContentManager
import org.apache.jackrabbit.ocm.manager.impl.ObjectContentManagerImpl
import org.apache.jackrabbit.ocm.mapper.Mapper
import org.apache.jackrabbit.ocm.mapper.impl.annotation.AnnotationMapperImpl
import org.apache.jackrabbit.ocm.reflection.ReflectionUtils

import javax.jcr.NodeIterator
import javax.jcr.Repository
import javax.jcr.Session
import javax.jcr.SimpleCredentials
import javax.jcr.Node

import static org.junit.Assert.*
import org.junit.*

class MainTestTests {
    def grailsApplication

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testMain() {
        String host = "http://bur2-d1036236.eac.ad.ea.com:4502/crx/server"
//        String host = "http://localhost:4502/crx/server"
        Repository repository = JcrUtils.getRepository(host)

        //Workspace Login
        SimpleCredentials creds = new SimpleCredentials("admin", "admin".toCharArray());
        Session session = repository.login(creds, "crx.default");
//        session.getWorkspace().getNamespaceRegistry().registerNamespace("ocm", "http://jackrabbit.apache.org/ocm");

        //List Children
        System.out.println("Workspace: " + session.getWorkspace().getName() + "\n");

//        ReflectionUtils.setClassLoader(Thread.currentThread().getContextClassLoader());

        List<Class> classes = new ArrayList<Class>();
//        classes.add(PageContent.class)
//        classes.add(Header.class)
        classes.add(JcrNode.class)
        classes.add(Text.class)
        classes.add(CharacterPage.class)

        Mapper mapper = new AnnotationMapperImpl(classes);
        ObjectContentManager ocm =  new ObjectContentManagerImpl(session, mapper);

//        Object o = ocm.getObject('/content/geometrixx/en/jcr:content')
        Object o = ocm.getObject('/content/inquisition-dragonage/en_US/characters/humans/morrigan/jcr:content')


//        String[] xmls = ["grails-app"+ File.separator +  "conf" + File.separator + "resource" + File.separator + "jcrmapping.xml"]
//
//        ObjectContentManager ocmXmlConfig = new ObjectContentManagerImpl(session, xmls)
//        Object o1 = ocmXmlConfig.getObject('/content/geometrixx/en/jcr:content')



        // 1. take the object and determine it's resource type


        // 2. check the resource type against the mapping from config file
        // 3. instantiate that object with the resource type mapping
        // 4. copy all values from object to the resource type instance
        // 5. cache object based on path
        // 6. return object

        Node node = session.getNode('/content/geometrixx/en/jcr:content')
        System.out.println("name: ${node.getName()}, resourceType: ${node.getProperty('sling:resourceType')?.getString()}");
        NodeIterator ni = node.getNodes();
        while(ni.hasNext()) {
            Node n = ni.nextNode()
            System.out.println("  " + n.getName());

        }

        assertTrue(true)
    }

    @Test
    void test_plugin() {
        PersistentManager pm = (PersistentManager) grailsApplication.mainContext.getBean('persistentManager')
        Object o = pm.getObject('/content/inquisition-dragonage/en_US/characters/humans/morrigan/jcr:content')
        grailsApplication.mainContext
    }
}
