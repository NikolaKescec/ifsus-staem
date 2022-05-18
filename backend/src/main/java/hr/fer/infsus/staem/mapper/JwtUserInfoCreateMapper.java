package hr.fer.infsus.staem.mapper;

import hr.fer.infsus.staem.mapper.core.CreateMapper;
import hr.fer.infsus.staem.security.UserInfo;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class JwtUserInfoCreateMapper implements CreateMapper<Object, UserInfo> {

    @Override
    public UserInfo map(Object jwt) {
        final UserInfo userInfo = new UserInfo();

        if (jwt instanceof final Jwt jwtToken) {
            userInfo.setSubject(jwtToken.getSubject());
            userInfo.setEmail(jwtToken.getClaimAsString("https://staem-email"));
        }

        return userInfo;
    }

}
