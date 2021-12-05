package com.example.votingsystem.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
//  from import org.springframework.security.core.GrantedAuthority;
//        implements GrantedAuthority {

    ADMIN(Permission.allPermissions()),
    MODERATOR(Set.of(Permission.VOTE_READ, Permission.VOTE_WRITE)),
    USER(Set.of(Permission.VOTE_API_MAIN));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> authorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }

    //    https://stackoverflow.com/a/19542316/548473
//    @Override
//    public String getAuthority() {
//        return "ROLE_" + name();
//    }
}
