package ind.lgh.system.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Transient;

/**
 * 系统用户
 *
 * @author lgh
 * @since 2017-12-21
 */
@Getter
@Setter
@ToString
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = -1L;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 账号失效状态
     * true已失效，false未失效
     */
    private Boolean accountExpired = false;

    /**
     * 加密后的密码
     * 不返回的属性
     */
    @JSONField(serialize = false)
    private String hashedPassword;

    /**
     * 密码明文（不存入数据库）
     */
    @Transient
    private String password;

    /**
     * phone
     */
    private String phone;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * email
     */
    private String email;

}