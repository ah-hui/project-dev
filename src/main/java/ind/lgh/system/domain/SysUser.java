package ind.lgh.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author lgh
 */
@Getter
@Setter
@Entity
@ToString
@Table(name = "sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户账号 不能为空
     */
    @Column(name = "name", length = 15)
    private String name;

    /**
     * 账号是否有效
     * true有效
     * false无效
     */
    @Column(name = "is_valid", length = 15)
    private Boolean isValid;

    /**
     * 用户token
     */
    @Column(name = "token", length = 550)
    @JsonIgnore
    private String token = "";

    /**
     * 用户昵称, 不能为空
     */
    @Column(length = 15)
    private String nickName;

    /**
     * 用户密码(加密), 不能为空
     */
    @Column(name = "password", length = 150)
    @JsonIgnore
    private String password;

    /**
     * 用户密码(明文), 不能为空
     */
    @Column(name = "clearpassword")
    private String clearPassword;

    /**
     * 用户email
     */
    @Column(name = "email", length = 50)
    private String email;

    public  SysUser(){}

    public SysUser(String name, String nickName, String password,String clearPassword){
        this.name = name;
        this.nickName = name;
        this.password = password;
        this.clearPassword = clearPassword;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * Spring Data JPA内置乐观锁的实现，给数据库表添加乐观锁很简单，添加一个整型字段，
     * 并加入@Version注解就可以了，每次提交数据时JPA会自动检查版本
     * 如何实现呢？ 它是为数据库表增加一个标识数据版本的version字段来实现的，读取数据时把version字段一同读出，
     * 写入数据库时比对version字段就知道数据是否被更改过，
     * 如果version不相等就说明持有的是过期数据，不能写入，
     * 如果相等就可以写入，并把version加 1。
     */
    @Version
    @Column(name = "version", length = 100)
    @JsonIgnore
    private Integer version;

    /**
     * 记录是否逻辑删除 。true 已删除，false 未删除
     */
    @Column(name = "is_deleted", length = 2)
    @JsonIgnore
    private boolean isDeleted = false;
    /**
     * 创建时间
     */
    @Column(name = "create_time", length = 122)
    private String createTime = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss", Locale.getDefault() ).format( new Date() );
    /**
     * 创建人
     */
    @Column(name = "create_by", length = 25)
    @JsonIgnore
    private Integer createBy;
    /**
     * 修改时间
     */
    @Column(name = "update_time", length = 122)
    private String updateTime;
    /**
     * 修改人
     */
    @Column(name = "update_by", length = 25)
    @JsonIgnore
    private Integer updateBy;
    /**
     * 删除时间
     */
    @Column(name = "delete_time", length = 122)
    @JsonIgnore
    private String deleteTime;
    /**
     * 删除人
     */
    @Column(name = "delete_by", length = 25)
    @JsonIgnore
    private Integer deleteBy;
    /**
     * 备注
     */
    @Column(name = "remarks", length = 255)
    @JsonIgnore
    private String remarks;

}
