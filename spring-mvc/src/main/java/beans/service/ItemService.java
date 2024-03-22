package beans.service;

import beans.repository.ItemRepository;
import beans.repository.entity.Item;
import beans.service.mapper.ItemMapper;
import beans.service.model.ItemModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;
    private final ItemMapper mapper;

    public List<ItemModel> findAll() {
        return repository.findAll().stream().map(mapper::map).toList();
    }

    public ItemModel findById(Integer id) {
        return mapper.map(repository.findById(id).orElseThrow(() -> new EntityNotFoundException("no Item found for id " + id)));
    }

    public void save(ItemModel model) {
        Item item = mapper.map(model);
        repository.save(item);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

}
