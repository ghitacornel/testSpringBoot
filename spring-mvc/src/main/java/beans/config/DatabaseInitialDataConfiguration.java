package beans.config;

import beans.service.ItemService;
import beans.service.model.ItemModel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Date;

@Configuration
@RequiredArgsConstructor
class DatabaseInitialDataConfiguration {

    private final ItemService service;

    @PostConstruct
    public void setupDatabaseData() {
        service.save(ItemModel.builder().name("product 1").quantity(11).price(11.11D).registerDate(new Date()).build());
        service.save(ItemModel.builder().name("product 2").quantity(22).price(22.22D).registerDate(new Date()).build());
        service.save(ItemModel.builder().name("product 3").quantity(33).price(33.33D).registerDate(new Date()).build());
    }

}
