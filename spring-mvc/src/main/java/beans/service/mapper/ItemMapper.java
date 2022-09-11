package beans.service.mapper;

import beans.repository.entity.Item;
import beans.service.model.ItemModel;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public Item map(ItemModel model) {
        return Item.builder()
                .id(model.getId())
                .name(model.getName())
                .quantity(model.getQuantity())
                .price(model.getPrice())
                .registerDate(model.getRegisterDate())
                .build();
    }

    public ItemModel map(Item item) {
        return ItemModel.builder()
                .id(item.getId())
                .name(item.getName())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .registerDate(item.getRegisterDate())
                .build();
    }

}
