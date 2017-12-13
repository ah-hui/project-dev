package ind.lgh.system.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 简单UserRoleMenu权限模型--菜单（角色权限中间表）
 *
 * @author lgh
 */
@Getter
@Setter
@Entity
@Table(name = "simple_role_menu")
public class SimpleRoleMenu extends BaseEntity {

    /**
     * 角色id
     */
    @Column(name = "role_id", nullable = false, columnDefinition = "INT(19)")
    private Integer roleId;

    /**
     * 权限id
     */
    @Column(name = "menu_id", nullable = false, columnDefinition = "INT(19)")
    private Integer menuId;
}
