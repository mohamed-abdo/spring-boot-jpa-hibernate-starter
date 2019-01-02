package com.softideas.entities;

public enum RoleCode {
    ADMIN("ADMIN"), DEFAULT("DEFAULT"), DEVELOPER("DEV");

    private final String role;

    RoleCode(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static RoleCode fromRoleType(String role) {
        switch (role) {
            case "ADMIN":
                return RoleCode.ADMIN;
            case "DEV":
                return RoleCode.DEVELOPER;
            default:
                return RoleCode.DEFAULT;
        }
    }

}
