package hr.fer.infsus.staem.service.impl;

import hr.fer.infsus.staem.entity.Developer;
import hr.fer.infsus.staem.exception.EntityNotFoundException;
import hr.fer.infsus.staem.repository.DeveloperRepository;
import hr.fer.infsus.staem.service.DeveloperQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeveloperQueryServiceImpl implements DeveloperQueryService {

    private final DeveloperRepository developerRepository;

    @Override
    public List<Developer> findAll() {
        return developerRepository.findAll();
    }

    @Override
    public Developer findById(Long id) {
        return developerRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(Developer.class, new String[] { "id" }));
    }

}
