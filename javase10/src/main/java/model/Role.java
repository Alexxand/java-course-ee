package model;

import lombok.Value;

import java.util.List;


@Value
public class Role {
    private String name;
    private List<Permission> permissions;

    void addPermission(Permission permission){
        permissions.add(permission);
    }

    boolean removePermission(Permission permission){
        return permissions.remove(permission);
    }
}
