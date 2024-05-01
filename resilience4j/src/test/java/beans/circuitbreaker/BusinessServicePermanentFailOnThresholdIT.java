package beans.circuitbreaker;

import beans.services.BusinessServicePermanentFailOnThreshold;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Supplier;

@SpringBootTest
class BusinessServicePermanentFailOnThresholdIT {

    @Autowired
    BusinessServicePermanentFailOnThreshold service;

    @Autowired
    CircuitBreakerRegistry circuitBreakerRegistry;

    @Test
    public void doSomething() {

        service.internalCounter = 0;
        service.threshold = 3;

        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("BusinessServicePermanentFailOnThresholdIT", CircuitBreakerConfig.custom()
                .failureRateThreshold(1)
                .slidingWindowSize(2)
                .build());

        Supplier<String> decoratedSupplier = CircuitBreaker
                .decorateSupplier(circuitBreaker, service::doSomething);

        Assertions.assertThat(service.internalCounter).isEqualTo(0);
        Assertions.assertThat(circuitBreaker.getState()).isEqualTo(CircuitBreaker.State.CLOSED);
        Assertions.assertThat(decoratedSupplier.get()).isEqualTo("1");
        Assertions.assertThat(circuitBreaker.getState()).isEqualTo(CircuitBreaker.State.CLOSED);
        Assertions.assertThat(service.internalCounter).isEqualTo(1);

        Assertions.assertThat(service.internalCounter).isEqualTo(1);
        Assertions.assertThat(decoratedSupplier.get()).isEqualTo("2");
        Assertions.assertThat(circuitBreaker.getState()).isEqualTo(CircuitBreaker.State.CLOSED);
        Assertions.assertThat(service.internalCounter).isEqualTo(2);

        Assertions.assertThat(service.internalCounter).isEqualTo(2);
        Assertions.assertThat(decoratedSupplier.get()).isEqualTo("3");
        Assertions.assertThat(circuitBreaker.getState()).isEqualTo(CircuitBreaker.State.CLOSED);
        Assertions.assertThat(service.internalCounter).isEqualTo(3);

        Assertions.assertThat(service.internalCounter).isEqualTo(3);
        Assertions.assertThat(decoratedSupplier.get()).isEqualTo("4");
        Assertions.assertThat(circuitBreaker.getState()).isEqualTo(CircuitBreaker.State.CLOSED);
        Assertions.assertThat(service.internalCounter).isEqualTo(4);

        Assertions.assertThat(service.internalCounter).isEqualTo(4);
        Assertions.assertThatExceptionOfType(RuntimeException.class).isThrownBy(decoratedSupplier::get);
        Assertions.assertThat(service.internalCounter).isEqualTo(4);
        Assertions.assertThat(circuitBreaker.getState()).isEqualTo(CircuitBreaker.State.OPEN);

        Assertions.assertThat(service.internalCounter).isEqualTo(4);
        Assertions.assertThatExceptionOfType(RuntimeException.class).isThrownBy(decoratedSupplier::get);
        Assertions.assertThat(service.internalCounter).isEqualTo(4);
        Assertions.assertThat(circuitBreaker.getState()).isEqualTo(CircuitBreaker.State.OPEN);

        Assertions.assertThat(service.internalCounter).isEqualTo(4);
        Assertions.assertThatExceptionOfType(RuntimeException.class).isThrownBy(decoratedSupplier::get);
        Assertions.assertThat(service.internalCounter).isEqualTo(4);
        Assertions.assertThat(circuitBreaker.getState()).isEqualTo(CircuitBreaker.State.OPEN);

    }

}
