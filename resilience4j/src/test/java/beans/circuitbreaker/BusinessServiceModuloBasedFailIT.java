package beans.circuitbreaker;

import beans.services.BusinessServiceModuloBasedFail;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Supplier;

@SpringBootTest
class BusinessServiceModuloBasedFailIT {

    @Autowired
    BusinessServiceModuloBasedFail service;

    @Autowired
    CircuitBreakerRegistry circuitBreakerRegistry;

    @Test
    public void doSomething() {

        service.internalCounter = 0;
        service.modulo = 3;

        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("BusinessServiceModuloBasedFailIT", CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .slidingWindowSize(3)
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
        Assertions.assertThatExceptionOfType(RuntimeException.class).isThrownBy(decoratedSupplier::get);
        Assertions.assertThat(service.internalCounter).isEqualTo(3);
        Assertions.assertThat(circuitBreaker.getState()).isEqualTo(CircuitBreaker.State.CLOSED);

        Assertions.assertThat(service.internalCounter).isEqualTo(3);
        Assertions.assertThat(circuitBreaker.getState()).isEqualTo(CircuitBreaker.State.CLOSED);
        Assertions.assertThat(decoratedSupplier.get()).isEqualTo("4");
        Assertions.assertThat(circuitBreaker.getState()).isEqualTo(CircuitBreaker.State.CLOSED);
        Assertions.assertThat(service.internalCounter).isEqualTo(4);

        Assertions.assertThat(service.internalCounter).isEqualTo(4);
        Assertions.assertThat(decoratedSupplier.get()).isEqualTo("5");
        Assertions.assertThat(circuitBreaker.getState()).isEqualTo(CircuitBreaker.State.CLOSED);
        Assertions.assertThat(service.internalCounter).isEqualTo(5);

        Assertions.assertThat(service.internalCounter).isEqualTo(5);
        Assertions.assertThatExceptionOfType(RuntimeException.class).isThrownBy(decoratedSupplier::get);
        Assertions.assertThat(service.internalCounter).isEqualTo(6);
        Assertions.assertThat(circuitBreaker.getState()).isEqualTo(CircuitBreaker.State.CLOSED);

    }

}
