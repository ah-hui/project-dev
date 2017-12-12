package ind.lgh.system.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 简单UserRoleMenu权限模型--菜单（用户角色中间表）
 *
 * @author lgh
 */
@Getter
@Setter
@Entity
@Table(name = "simple_user_role")
public class SimpleUserRole extends BaseEntity {

    /**
     * 用户id
     */
    @Column(name = "user_id", nullable = false, columnDefinition = "INT(19)")
    private Integer userId;

    /**
     * 角色id
     */
    @Column(name = "role_id", nullable = false, columnDefinition = "INT(19)")
    private Integer roleId;

}
