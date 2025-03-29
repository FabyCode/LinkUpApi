package com.linkup.api.security;

import com.linkup.api.model.Emprendedor;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class EmprendedorAuthenticationToken extends AbstractAuthenticationToken {
    private final Emprendedor emprendedor;

    public EmprendedorAuthenticationToken(Emprendedor emprendedor) {
        super(null);
        this.emprendedor = emprendedor;
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return emprendedor;
    }
}
