package hr.fer.infsus.staem.testBuilders;

import hr.fer.infsus.staem.security.UserInfo;

public final class UserInfoTestBuilder {

    private final UserInfo userInfo;

    private UserInfoTestBuilder() {
        this.userInfo = new UserInfo();
    }

    public UserInfo build() {
        return this.userInfo;
    }

    public static UserInfoTestBuilder builder() {
        return new UserInfoTestBuilder();
    }

    public UserInfoTestBuilder def() {
        this.userInfo.setSubject("subject");
        this.userInfo.setEmail("email");

        return this;
    }

    public UserInfoTestBuilder withSubject(String subject) {
        this.userInfo.setSubject(subject);

        return this;
    }

    public UserInfoTestBuilder withEmail(String email) {
        this.userInfo.setEmail(email);

        return this;
    }

}
