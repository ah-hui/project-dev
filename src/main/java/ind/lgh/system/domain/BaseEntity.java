package ind.lgh.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类
 * <p>标注为@MappedSuperclass，表示此类不是一个完整的实体类，它将不会映射到数据库表，但是他的属性都将映射到其子类的数据库字段中。</p>
 * <p>@Column注解length只支持指定String长度，如果要指定其他类型（如Integer）则要使用columnDefinition，然而可能导致移植性和数据库兼容性问题</p>
 *
 * @author lgh
 * @since 2017-12-05
 */
@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT(19) COMMENT '主键'")
    private Integer id;

    /**
     * Spring Data JPA内置乐观锁的实现，
     * 给数据库表添加乐观锁很简单，@Version注解一个整型字段即可，
     * 每次提交数据时JPA会自动检查和更新版本
     */
    @JsonIgnore
    @Version
    @Column(name = "version", columnDefinition = "INT(19) DEFAULT 0 COMMENT '版本号'")
    private Integer version;

    /**
     * 逻辑删除
     * true 已删除，false 未删除
     * boolean型由JPA自动映射为数据库的bit(1)
     */
    @JsonIgnore
    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    /**
     * 创建人
     */
    @JsonIgnore
    @Column(name = "created_by", columnDefinition = "INT(19) DEFAULT -1 COMMENT '创建人'")
    private Integer createdBy;

    /**
     * 创建时间
     * TODO：getter需不需要加工？
     */
    @Column(name = "date_created")
    private Date dateCreated;

    /**
     * 最后修改人
     */
    @JsonIgnore
    @Column(name = "updated_by", columnDefinition = "INT(19) DEFAULT -1 COMMENT '最后修改人'")
    private Integer updatedBy;

    /**
     * 最后修改时间
     */
    @Column(name = "last_updated")
    private Date lastUpdated;

    /**
     * 备注
     */
    @JsonIgnore
    @Column(name = "remarks", length = 255)
    private String remarks;

}
