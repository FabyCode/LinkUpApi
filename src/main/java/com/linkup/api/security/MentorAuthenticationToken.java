package com.linkup.api.security;

import com.linkup.api.model.Mentor;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class MentorAuthenticationToken extends AbstractAuthenticationToken {
    private final Mentor mentor;

    public MentorAuthenticationToken(Mentor mentor) {
        super(null);
        this.mentor = mentor;
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return mentor;
    }
}
