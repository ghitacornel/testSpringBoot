package beans.services;

import beans.model.Product;
import beans.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<Product> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Product> findByCustomQuery(String searchTerm) {
        return repository.findByCustomQuery(searchTerm, PageRequest.of(0, 10)).stream().toList();
    }

    public List<Product> findByNamedQuery(String searchTerm) {
        return repository.findByNamedQuery(searchTerm, PageRequest.of(0, 10)).stream().toList();
    }

    public void save(Product phone) {
        repository.save(phone, Duration.ZERO);
    }

    public List<Product> findAll() {
        return repository.findAll(PageRequest.of(0, Integer.MAX_VALUE)).stream().toList();
    }
}
