package beans.events.asynchronous;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsynchronousConfiguration implements AsyncConfigurer {

    @Bean// not required to expose this as a bean, we need it for test purpose only
    @Override
    public ThreadPoolTaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setWaitForTasksToCompleteOnShutdown(true);// mandatory for test purpose
        executor.setCorePoolSize(2);// at least 2 threads for 2 expected events
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(1);// force it to 1 in order to force 1 thread per event processing
        executor.setThreadNamePrefix("MyExecutor-");
        executor.initialize();
        return executor;
    }
}
