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
    CustomService customService;

    @Mock
    CustomServiceDependency customServiceDependency;

    @Test
    void returnString() {
        Mockito.when(customServiceDependency.returnString()).thenReturn("dummy");
        String result = customService.returnString();
        Assertions.assertEquals("dummy", result);
    }
}
