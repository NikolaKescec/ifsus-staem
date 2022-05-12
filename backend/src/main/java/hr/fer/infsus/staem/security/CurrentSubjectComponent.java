package hr.fer.infsus.staem.security;

import hr.fer.infsus.staem.mapper.core.CreateMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@AllArgsConstructor
public class CurrentSubjectComponent {

    private final CreateMapper<Object, String> subjectCreateMapper;

    @Bean
    public Function<Object, String> currentUser() {
        return subjectCreateMapper::map;
    }

}
