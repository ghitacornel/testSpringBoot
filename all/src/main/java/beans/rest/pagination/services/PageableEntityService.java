package beans.rest.pagination.services;

import beans.rest.pagination.controllers.PageableDto;
import beans.rest.pagination.controllers.SortDto;
import beans.rest.pagination.repositories.PageableEntity;
import beans.rest.pagination.repositories.PageableEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PageableEntityService {

    private final PageableEntityRepository repository;

    @PostConstruct
    public void insertSomeDummyData() {
        repository.deleteAll();
        for (int i = 0; i < 100; i++) {
            PageableEntity pageableEntity = new PageableEntity();
            pageableEntity.setData("dummy data " + i);
            repository.save(pageableEntity);
        }
    }

    public List<PageableEntity> findAll() {
        return repository.findAll();
    }

    public List<PageableEntity> findAll(SortDto sortDto) {
        Sort sort = sortDto.getOrder().equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortDto.getProperty()).ascending()
                : Sort.by(sortDto.getProperty()).descending();
        return repository.findAll(sort);
    }

    public Page<PageableEntity> findAll(PageableDto pageableDto) {
        Sort sort = pageableDto.getOrder().equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(pageableDto.getProperty()).ascending()
                : Sort.by(pageableDto.getProperty()).descending();
        Pageable pageable = PageRequest.of(pageableDto.getPage(), pageableDto.getSize(), sort);
        return repository.findAll(pageable);
    }

}
