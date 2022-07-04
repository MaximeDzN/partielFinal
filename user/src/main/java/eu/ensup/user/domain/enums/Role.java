package eu.ensup.user.domain.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_DIRECTOR,
    ROLE_RESPONSABLE;

    @Override
    public String getAuthority() {
        return name();
    }
}