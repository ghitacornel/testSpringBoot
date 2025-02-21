package testSpringBoot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomServiceTest {

    @InjectMocks
    CustomService service;

    @Mock
    CustomServiceHelper helper;

    @Test
    void returnString() {
        Mockito.when(helper.returnString()).thenReturn("dummy");
        String result = service.returnString();
        Assertions.assertEquals("dummy", result);
    }
}
