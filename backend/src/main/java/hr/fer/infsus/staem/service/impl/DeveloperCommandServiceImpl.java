package hr.fer.infsus.staem.service.impl;

import hr.fer.infsus.staem.entity.Developer;
import hr.fer.infsus.staem.repository.DeveloperRepository;
import hr.fer.infsus.staem.service.DeveloperCommandService;
import hr.fer.infsus.staem.service.DeveloperQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeveloperCommandServiceImpl implements DeveloperCommandService {

    private final DeveloperRepository developerRepository;

    private final DeveloperQueryService developerQueryService;

    @Override
    public Developer create(String name) {
        final Developer developer = new Developer();
        developer.setName(name);

        return developerRepository.save(developer);
    }

    @Override
    public Developer update(Long id, String name) {
        final Developer developer = developerQueryService.findById(id);
        developer.setName(name);

        return developerRepository.save(developer);
    }

    @Override
    public void delete(Long id) {
        final Developer developer = developerQueryService.findById(id);

        developerRepository.delete(developer);
    }

}
