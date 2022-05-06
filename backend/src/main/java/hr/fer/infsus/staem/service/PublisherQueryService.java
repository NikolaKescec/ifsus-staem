package hr.fer.infsus.staem.service;

import hr.fer.infsus.staem.entity.Publisher;

import java.util.List;

public interface PublisherQueryService {

    List<Publisher> findAll();

    Publisher findById(Long id);

}
