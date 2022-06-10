package beans.service;

import beans.model.Item;
import beans.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    public List<Item> findAll() {
        return repository.findAll();
    }

}
