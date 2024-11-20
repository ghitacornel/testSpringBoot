package beans;

import beans.model.Product;
import beans.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SolrIT {

    @Autowired
    ProductService productService;

    @Test
    public void test() {

        Assertions.assertTrue(productService.findAll().isEmpty());

        Product phone = new Product();
        phone.setId("P0001");
        phone.setName("Phone");

        productService.save(phone);

        Assertions.assertEquals(1, productService.findAll().size());
    }

}
