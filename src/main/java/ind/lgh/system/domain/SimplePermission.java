package ind.lgh.system.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * RBAC模型--权限
 *
 * @author lgh
 * @since 2017-12-21
 */
@Getter
@Setter
@Entity
@Table(name = "simple_permission")
public class SimplePermission extends BaseEntity {

    /**
     * 权限字符串
     * menu如：role:*
     * button如：role:create,role:update,role:delete,role:view
     */
    @Column(name = "permission", length = 240)
    private String permission;

    /**
     * 权限名称
     * UI界面显示
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * 资源类型
     * [menu|button]
     */
    @Column(name = "resource_type", columnDefinition = "enum('menu','button')")
    private String resourceType;

    /**
     * 资源地址
     */
    @Column(name = "url", length = 100)
    private String url;

    /**
     * 父编号
     */
    @Column(name = "parent_id", columnDefinition = "INT(19) COMMENT '父编号'")
    private Integer parentId;

    /**
     * 父编号列表
     */
    @Column(name = "parent_ids", length = 60)
    private String parentIds;

    /**
     * 是否有效
     * true 有效,false 无效
     */
    @Column(name = "valid")
    private Boolean valid = true;

    /**
     * 角色描述
     */
    @Column(name = "description", length = 240)
    private String description;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "SIMPLE_ROLE_PERMISSION", joinColumns = {
//            @JoinColumn(name = "PERMISSION_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
//            @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
//    private List<SimpleRole> roles;

}
