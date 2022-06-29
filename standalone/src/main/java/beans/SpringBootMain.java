package beans;

import beans.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@RequiredArgsConstructor
public class SpringBootMain implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMain.class, args);
    }

    private final ItemService itemService;

    @Override
    public void run(String... args) {
        // main job starts from here
        System.err.println(itemService.findAll());
        System.err.println(itemService.findByNameAndLength("calcium", 122));
    }

}
