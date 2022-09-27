package com.example.BackendApp.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permissions.USERS_READ)),
    ADMIN(Set.of(Permissions.USERS_WRITE, Permissions.USERS_READ));

    private final Set<Permissions> permissions;

    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permissions1 -> new SimpleGrantedAuthority(permissions1.getPermission()))
                .collect(Collectors.toSet());
    }
}
