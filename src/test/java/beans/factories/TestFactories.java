package beans.factories;

import org.junit.Assert;
import org.junit.Test;
import template.AbstractTestSpringContext;

public class TestFactories extends AbstractTestSpringContext {

    @Test
    public void testConfigurationFactoriesAreSingletonsByDefault() {

        ConfigurationFactory bean1 = context.getBean(ConfigurationFactory.class);
        Assert.assertNotNull(bean1);

        ConfigurationFactory bean2 = context.getBean(ConfigurationFactory.class);
        Assert.assertNotNull(bean2);

        Assert.assertSame(bean1, bean2);// observe configuration factories are automatically registered as singleton beans

    }

    @Test
    public void testFactoryProducts() {

        ConfigurationFactoryProductPrototype product_type_1_instance_1 = context.getBean(ConfigurationFactoryProductPrototype.class);
        Assert.assertNotNull(product_type_1_instance_1);
        Assert.assertEquals("producePrototype", product_type_1_instance_1.createdBy);// verify product is created using expected factory method

        ConfigurationFactoryProductPrototype product_type_1_instance_2 = context.getBean(ConfigurationFactoryProductPrototype.class);
        Assert.assertNotNull(product_type_1_instance_2);
        Assert.assertEquals("producePrototype", product_type_1_instance_2.createdBy);// verify product is created using expected factory method

        Assert.assertNotSame(product_type_1_instance_1, product_type_1_instance_2);// verify factory product scope is honored

        ConfigurationFactoryProductSingleton product_type_2_instance_1 = context.getBean(ConfigurationFactoryProductSingleton.class);
        Assert.assertNotNull(product_type_2_instance_1);
        Assert.assertEquals("produceSingleton", product_type_2_instance_1.createdBy);// verify product is created using expected factory method

        ConfigurationFactoryProductSingleton product_type_2_instance_2 = context.getBean(ConfigurationFactoryProductSingleton.class);
        Assert.assertNotNull(product_type_2_instance_2);
        Assert.assertEquals("produceSingleton", product_type_2_instance_2.createdBy);// verify product is created using expected factory method

        Assert.assertSame(product_type_2_instance_1, product_type_2_instance_2);// verify factory product scope is honored
    }
}
