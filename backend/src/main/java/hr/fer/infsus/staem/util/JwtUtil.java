package hr.fer.infsus.staem.util;

import org.springframework.security.oauth2.jwt.Jwt;

public final class JwtUtil {

    private JwtUtil() {
    }

    public static String getSubjectFromToken(Jwt token) {
        return token.getSubject();
    }

}
