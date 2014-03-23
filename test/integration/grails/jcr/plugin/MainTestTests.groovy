package grails.jcr.plugin

import com.p4f.jcr.JcrNode
import com.ea.core.JcrPersistentService
import com.p4f.jcr.components.Text
import com.ea.core.page.CharacterPage
import org.apache.jackrabbit.commons.JcrUtils
import org.apache.jackrabbit.ocm.manager.ObjectContentManager
import org.apache.jackrabbit.ocm.manager.impl.ObjectContentManagerImpl
import org.apache.jackrabbit.ocm.mapper.Mapper
import org.apache.jackrabbit.ocm.mapper.impl.annotation.AnnotationMapperImpl

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
        def x = grailsApplication.mainContext.getBean("jcrPersistentService")
        println "2 - ${x.ocmPool}"
        Object o = x.getObject('/content/inquisition-dragonage/en_US/characters/humans/morrigan/jcr:content')
        Object o1 = x.getObject('/content/inquisition-dragonage/en_US/characters/humans/morrigan/jcr:content')
        Object o2 = x.getObject('/content/inquisition-dragonage/en_US/characters/humans/morrigan/jcr:content')
        Object o3 = x.getObject('/content/inquisition-dragonage/en_US/characters/humans/morrigan/jcr:content')
        Object o4 = x.getObject('/content/inquisition-dragonage/en_US/characters/humans/morrigan/jcr:content')
        println "half way"
        Object o5 = x.getObject('/content/inquisition-dragonage/en_US/characters/humans/morrigan/jcr:content')
        Object o6 = x.getObject('/content/inquisition-dragonage/en_US/characters/humans/morrigan/jcr:content')
        Object o7 = x.getObject('/content/inquisition-dragonage/en_US/characters/humans/morrigan/jcr:content')
        println "done"

    }


    @Test
    void test_newLoad() {

//        int t = 100
//        final ScheduledExecutorService exec =
//            Executors.newScheduledThreadPool(t);
//
//            // Schedule first task
//        for (int i =0; i < t; i++) {
//            exec.scheduleAtFixedRate(new Thread() {
//                @Override
//                public void run() {
//                    start()
//                }
//
//                @Override
//                synchronized void start() {
//                    def x = grailsApplication.mainContext.getBean("jcrPersistentService")
//                    Object o = x.getObject('/content/inquisition-dragonage/en_US/characters/humans/morrigan/jcr:content')
//                    println "${i} - ${o.uuid}"
//                }
//            }, 10, 1, TimeUnit.MILLISECONDS);
//
//    }

            // Schedule second task
//            exec.scheduleAtFixedRate(new Runnable(){
//                @Override
//                public void run() {
//                    println "bbb"
//                }}, 0, 1, TimeUnit.MINUTES);

    for (int j=0; j < 5; j++) {
        Thread[] t = new Thread[10]
        for (int i=0; i < t.length; i++) {

            t[i] = new Thread(new Runnable() {
                @Override
                void run() {
                    Thread.sleep(500)
                    def x = grailsApplication.mainContext.getBean("jcrPersistentService")
                    Object y = x.getObject(CharacterPage.class, '/content/inquisition-dragonage/en_US/characters/humans/morrigan/jcr:content')
                    println "y - ${y.uuid}"
                    Object z = x.getObject('/content/inquisition-dragonage/en_US/characters/humans/morrigan/jcr:content')
                    println "z - ${z.uuid}"

                }
            })
        }


        for (int i=0; i < t.length; i++) {
            t[i].run()
        }
        Thread.sleep(1000)
    }
    }

    private void spawnThreads(int threads) {
        Thread[] t = new Thread[threads]
        for (int i=0; i < t.length; i++) {

            t[i] = new Thread(new Runnable() {
                @Override
                void run() {
//                    Thread.sleep(500)
                    def x = grailsApplication.mainContext.getBean("jcrPersistentService")
                    def abc = (new Random()).nextInt()
//                    if (abc % 2 == 0) {
                    Object y = x.getObject(CharacterPage.class, '/content/inquisition-dragonage/en_US/characters/humans/morrigan/jcr:content')
                    println "even ${abc} - ${y.uuid}"
//                        Thread.sleep(200)
//                }
                    Object z = x.getObject('/content/inquisition-dragonage/en_US/characters/humans/morrigan/jcr:content')
                    println "odd ${abc} - ${z.uuid}"
//                        Thread.sleep(800)


                }
            })
        }


        for (int i=0; i < t.length; i++) {
            t[i].run()
        }
    }

    @Test
    void test_longLoad() {
        for (int i=1; i < 2; i++) {
            spawnThreads(500)
            Thread.sleep(1000)
        }
    }

    @Test
    void test_load() {
        JcrPersistentService xyz = (JcrPersistentService) grailsApplication.mainContext.getBean("jcrPersistentService")
        println "hxyz - ${xyz.ocmPool}"

        Thread[] threads = new Thread[10];
        for (int i =0 ; i < threads.length; i++) {
            JcrPersistentService xy = (JcrPersistentService) grailsApplication.mainContext.getBean("jcrPersistentService")
            println "hahha - ${xy.ocmPool}"

            threads[i] = new Thread( new Runnable() {

                @Override
                void run() {
                    try {
                        println "1"
                        JcrPersistentService x = (JcrPersistentService) grailsApplication.mainContext.getBean("jcrPersistentService")
                        println "2 - ${x.ocmPool}"
//                        Object o = x.getObject('/content/inquisition-dragonage/en_US/characters/humans/morrigan/jcr:content')
                        println "3"
//                        println "${o.uuid}"

                    } catch (Exception e) {
                        println e.getMessage()
                    }
//                    assertEquals('14da448f9-8f3b-4fb2-bd5f-d44f495b9610', o.uuid)
                }
            })
        }

        for (int i=0; i < threads.length; i++) {
            threads[i].start()
        }

    }

    public final void output() {
        try {
            def x = grailsApplication.mainContext.getBean("jcrPersistentService")
            Object o = x.getObject('/content/inquisition-dragonage/en_US/characters/humans/morrigan/jcr:content')
        } catch (Exception e) {
            println e.getMessage()
        }
//        println("in thread : ${o.uuid}")
    }
}
