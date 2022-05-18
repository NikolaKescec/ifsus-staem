package hr.fer.infsus.staem.security;

import hr.fer.infsus.staem.mapper.core.CreateMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@AllArgsConstructor
public class CurrentAnnotationHelper {

    private final CreateMapper<Object, UserInfo> jwtUserInfoCreateMapper;

    @Bean
    public Function<Object, UserInfo> currentUserInfo() {
        return jwtUserInfoCreateMapper::map;
    }

}
