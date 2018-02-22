package model;

import javax.persistence.*;

@Table(name = "simple_role_menu")
public class SimpleRoleMenu {
    @Id
    @Column(name = "menu_id")
    private Integer menuId;

    @Id
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * @return menu_id
     */
    public Integer getMenuId() {
        return menuId;
    }

    /**
     * @param menuId
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
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