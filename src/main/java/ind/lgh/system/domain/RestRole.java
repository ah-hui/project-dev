package ind.lgh.system.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 简单UserRoleMenu权限模型--角色
 * RESTful登录专用
 *
 * @author lgh
 */
@Getter
@Setter
@Entity
@Table(name = "rest_role")
public class RestRole extends BaseEntity {

    /**
     * 角色code
     */
    @Column(name = "code", length = 30, nullable = false)
    private String code;

    /**
     * 角色名称
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * 是否有效
     * true 有效,false 无效
     */
    @Column(name = "is_valid")
    private Boolean isValid = true;

    /**
     * 角色描述
     */
    @Column(name = "description", length = 240)
    private String description;

}
