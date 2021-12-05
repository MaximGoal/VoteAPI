package com.example.votingsystem.model;

import java.util.Set;

public enum Permission {
    VOTE_API_MAIN("voteAPI:main"),
    VOTE_READ("vote:read"),
    VOTE_WRITE("vote:write"),
    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    public static Set<Permission> allPermissions() {
        return Set.of(VOTE_API_MAIN, VOTE_READ, VOTE_WRITE, USER_READ, USER_WRITE);
    }
}
