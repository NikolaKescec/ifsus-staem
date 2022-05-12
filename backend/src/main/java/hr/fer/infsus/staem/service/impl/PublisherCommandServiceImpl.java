package hr.fer.infsus.staem.service.impl;

import hr.fer.infsus.staem.entity.Publisher;
import hr.fer.infsus.staem.repository.PublisherRepository;
import hr.fer.infsus.staem.service.PublisherCommandService;
import hr.fer.infsus.staem.service.PublisherQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PublisherCommandServiceImpl implements PublisherCommandService {

    private final PublisherRepository publisherRepository;

    private final PublisherQueryService publisherQueryService;

    @Override
    public Publisher create(String name) {
        final Publisher publisher = new Publisher();
        publisher.setName(name);

        return publisherRepository.save(publisher);
    }

    @Override
    public Publisher update(Long id, String name) {
        final Publisher publisher = publisherQueryService.findById(id);
        publisher.setName(name);

        return publisherRepository.save(publisher);
    }

    @Override
    public void delete(Long id) {
        publisherRepository.deleteById(id);
    }

}
