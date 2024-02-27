package beans;

import beans.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@RequiredArgsConstructor
@Slf4j
public class SpringBootStandaloneMain implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStandaloneMain.class, args);
    }

    private final ItemService itemService;

    @Override
    public void run(String... args) {
        // main job starts from here
        log.error(String.valueOf(itemService.findAll()));
        log.error(String.valueOf(itemService.findByNameAndLength("calcium", 122)));
    }

}
