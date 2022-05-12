package hr.fer.infsus.staem.mapper;

import hr.fer.infsus.staem.mapper.core.CreateMapper;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class JwtSubjectStringCreateMapper implements CreateMapper<Object, String> {

    @Override
    public String map(Object jwt) {
        if (!(jwt instanceof Jwt)) {
            return null;
        }

        return ((Jwt) jwt).getSubject();
    }

}
