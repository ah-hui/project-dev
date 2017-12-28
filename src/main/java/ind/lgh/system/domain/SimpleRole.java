package ind.lgh.system.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * RBAC模型--角色
 *
 * @author lgh
 * @since 2017-12-21
 */
@Getter
@Setter
@Entity
@Table(name = "simple_role")
public class SimpleRole extends BaseEntity {

    /**
     * 角色code
     * 程序识别用，如admin，是唯一的
     */
    @Column(name = "code", length = 30, unique = true, nullable = false)
    private String code;

    /**
     * 角色名称
     * UI界面显示
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * 是否有效
     * true 有效,false 无效
     */
    @Column(name = "valid")
    private Boolean valid;

    /**
     * 角色描述
     */
    @Column(name = "description", length = 240)
    private String description;

    /**
     * 角色权限 - 多对多
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SIMPLE_ROLE_PERMISSION", joinColumns = {
            @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
            @JoinColumn(name = "PERMISSION_ID", referencedColumnName = "ID")})
    private List<SimplePermission> permissions;

    /**
     * 角色权限中间表list
     */
    @Transient
    private List<SimpleRolePermission> rolePermissions;

//    /**
//     * 用户角色 - 多对多
//     */
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "SIMPLE_USER_ROLE", joinColumns = {
//            @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
//            @JoinColumn(name = "USER_ID", referencedColumnName = "ID")})
//    private List<SysUser> users;

}
