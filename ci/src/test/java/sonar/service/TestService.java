package sonar.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestService {

    @Test
    public void test1() {
        Assertions.assertEquals(3, new BusinessService().sum(1, 2));
    }

    @Test
    public void test2() {
        Assertions.assertEquals(7, new BusinessService().sum(3, 4));
    }
}
