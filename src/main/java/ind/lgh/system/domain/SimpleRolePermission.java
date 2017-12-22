package ind.lgh.system.domain;

import ind.lgh.system.domain.compositepk.RolePermissionCompositePK;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * RBAC模型--角色权限中间表
 *
 * @author lgh
 * @since 2017-12-21
 */
@Getter
@Setter
@Entity
@Table(name = "simple_role_permission")
/* 标注用于标注实体所使用主键规则的类,另外要在实体中同时标注主键的属性 */
@IdClass(RolePermissionCompositePK.class)
public class SimpleRolePermission {

    /**
     * 角色id
     */
    @Id
    @Column(name = "role_id", nullable = false, columnDefinition = "INT(19)")
    private Integer roleId;

    /**
     * 权限id
     */
    @Id
    @Column(name = "permission_id", nullable = false, columnDefinition = "INT(19)")
    private Integer permissionId;
}
