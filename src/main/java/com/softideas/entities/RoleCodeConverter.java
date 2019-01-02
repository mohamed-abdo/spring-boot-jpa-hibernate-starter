package com.softideas.entities;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RoleCodeConverter implements AttributeConverter<RoleCode, String> {
    @Override
    public String convertToDatabaseColumn(RoleCode roleCode) {
        return roleCode.getRole();
    }

    @Override
    public RoleCode convertToEntityAttribute(String role) {
        return RoleCode.fromRoleType(role);
    }
}
