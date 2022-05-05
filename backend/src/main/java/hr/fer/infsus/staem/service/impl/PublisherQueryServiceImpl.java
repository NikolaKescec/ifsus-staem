package hr.fer.infsus.staem.service.impl;

import hr.fer.infsus.staem.entity.Publisher;
import hr.fer.infsus.staem.repository.PublisherRepository;
import hr.fer.infsus.staem.service.PublisherQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PublisherQueryServiceImpl implements PublisherQueryService {

    private final PublisherRepository publisherRepository;

    @Override
    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

}
