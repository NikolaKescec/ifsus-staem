package hr.fer.infsus.staem.service.impl;

import hr.fer.infsus.staem.entity.Users;
import hr.fer.infsus.staem.repository.UsersRepository;
import hr.fer.infsus.staem.service.UsersCommandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsersCommandServiceImpl implements UsersCommandService {

    private final UsersRepository usersRepository;

    @Override
    public Users create(String id) {
        final Users user = new Users();
        user.setId(id);

        return usersRepository.save(user);
    }

}
