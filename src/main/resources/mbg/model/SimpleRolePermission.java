package model;

import javax.persistence.*;

@Table(name = "simple_role_permission")
public class SimpleRolePermission {
    @Id
    @Column(name = "permission_id")
    private Integer permissionId;

    @Id
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * @return permission_id
     */
    public Integer getPermissionId() {
        return permissionId;
    }

    /**
     * @param permissionId
     */
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * @return role_id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}