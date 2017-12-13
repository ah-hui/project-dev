package ind.lgh.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * 系统用户
 *
 * @author lgh
 */
@Getter
@Setter
@Entity
@ToString
@Table(name = "sys_user")
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = -1L;

    /**
     * 登录名
     */
    @Column(name = "login_name", unique = true, length = 20, nullable = false)
    private String loginName;

    /**
     * 账号失效状态
     * true已失效，false未失效
     */
    @Column(name = "account_expired")
    private Boolean accountExpired = false;

    /**
     * 加密后的密码
     */
    @JsonIgnore
    @Column(name = "hashed_password", length = 240, nullable = false)
    private String hashedPassword;

    /**
     * 密码明文（不存入数据库）
     */
    @Transient
    private String password;

    /**
     * RESTful登录专用令牌token
     */
    @JsonIgnore
    @Column(name = "token", length = 255)
    private String token;

    /**
     * phone
     */
    @Column(name = "phone", unique = true, length = 20, nullable = false)
    private String phone;

    /**
     * 昵称
     */
    @Column(name = "nick_name", unique = true, length = 60)
    private String nickName;

    /**
     * email
     */
    @Column(name = "email", unique = true, length = 120)
    private String email;

    /**
     * 角色
     */
    @Transient
    private SimpleRole simpleRole;
//
//    /**
//     * 权限
//     */
//    @Transient
//    private List<Menu> menus;

}
