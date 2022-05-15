package hr.fer.infsus.staem.service.impl;

import hr.fer.infsus.staem.entity.Users;
import hr.fer.infsus.staem.repository.UsersRepository;
import hr.fer.infsus.staem.service.UsersQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsersQueryServiceImpl implements UsersQueryService {

    private final UsersRepository usersRepository;

    @Override
    public Users findById(String id) {
        if (id == null) {
            return null;
        }
        return usersRepository.findById(id).orElse(null);
    }

}
