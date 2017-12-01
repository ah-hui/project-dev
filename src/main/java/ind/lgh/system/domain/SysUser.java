package ind.lgh.system.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author lgh
 */
@Getter
@Setter
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false,unique = true)
    private String loginName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickName;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String regTime;

    public SysUser(String loginName,String password,String nickName,String phone,String email,String regTime){
        this.loginName = loginName;
        this.password = password;
        this.nickName = nickName;
        this.phone = phone;
        this.email = email;
        this.regTime = regTime;
    }

}
