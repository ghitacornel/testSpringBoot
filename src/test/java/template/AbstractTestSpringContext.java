package template;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class AbstractTestSpringContext {

    /**
     * bean container
     */
    protected static ClassPathXmlApplicationContext context;

    @BeforeClass
    public static void before() {
        context = new ClassPathXmlApplicationContext("spring-beans.xml");
    }

    @AfterClass
    public static void after() {
        context.close();
    }

}
