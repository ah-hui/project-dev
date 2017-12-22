package ind.lgh.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ind.lgh.system.config.shiro.PasswordManager;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * 系统用户
 *
 * @author lgh
 * @since 2017-12-21
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
     * 1.多对多关系
     * 2.级联：主控表信息改变时改变关联表-本例所有情况不进行级联操作 by default
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SIMPLE_USER_ROLE", joinColumns = {
            @JoinColumn(name = "USER_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    private List<SimpleRole> roles;

    /**
     * 密码盐.
     * 加盐后如果明文密码一样，由于盐不一样，密文就不一样
     *
     * @return
     */
    public String getCredentialsSalt() {
        return this.loginName + PasswordManager.getRawSalt();
    }

}
