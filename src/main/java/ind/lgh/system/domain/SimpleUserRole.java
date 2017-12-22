package ind.lgh.system.domain;

import ind.lgh.system.domain.compositepk.UserRoleCompositePK;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * RBAC模型--用户角色中间表
 *
 * 为什么使用复合主键?
 * 1.hibernate注解实现多对多关系时，默认会将中间表的主键设置为两关联字段的复合主键 --- 并且会忽略掉@Id和@GeneratedValue直接对关联主表对应字段(userId)自增 --- 进而导致本类的id字段无法自增要蛋疼自己设置
 * 2.第一条因素占比为80%，另外20%是想装比搞个高端的复合主键玩玩
 *
 * @author lgh
 * @since 2017-12-21
 */
@Getter
@Setter
@Entity
@Table(name = "simple_user_role")
/* 标注用于标注实体所使用主键规则的类,另外要在实体中同时标注主键的属性 */
@IdClass(UserRoleCompositePK.class)
public class SimpleUserRole {

    /**
     * 用户id
     */
    @Id
    @Column(name = "user_id", nullable = false, columnDefinition = "INT(19)")
    private Integer userId;

    /**
     * 角色id
     */
    @Id
    @Column(name = "role_id", nullable = false, columnDefinition = "INT(19)")
    private Integer roleId;

}
