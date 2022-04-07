package beans.pagination.service;

import beans.pagination.model.PageableDto;
import beans.pagination.model.SortDto;
import beans.pagination.repository.PageableEntityRepository;
import beans.pagination.repository.entity.PageableEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PageableEntityService {

    private final PageableEntityRepository repository;

    public List<PageableEntity> findAll() {
        return repository.findAll();
    }

    public List<PageableEntity> findAll(SortDto sortDto) {
        Sort sort = Sort.Direction.ASC.name().equalsIgnoreCase(sortDto.getOrder()) ?
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
