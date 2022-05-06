package hr.fer.infsus.staem.service;

import hr.fer.infsus.staem.entity.Publisher;

public interface PublisherCommandService {

    Publisher create(String name);

    Publisher update(Long id, String name);

    void delete(Long id);

}
