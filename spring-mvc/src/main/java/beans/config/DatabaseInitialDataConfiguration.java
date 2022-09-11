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
        service.save(ItemModel.builder().name("product 1").quantity(1).price(1D).registerDate(new Date()).build());
        service.save(ItemModel.builder().name("product 2").quantity(2).price(2D).registerDate(new Date()).build());
        service.save(ItemModel.builder().name("product 3").quantity(3).price(3D).registerDate(new Date()).build());
    }

}
