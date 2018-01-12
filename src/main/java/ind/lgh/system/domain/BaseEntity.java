package ind.lgh.system.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类
 *
 * @author lgh
 * @since 2017-12-05
 */
@Getter
@Setter
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer version;

    /**
     * 创建人
     * 不返回的属性
     */
    @JSONField(serialize = false)
    private Integer createdBy;

    /**
     * 创建时间
     * 返回格式化日期
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date dateCreated;

    /**
     * 最后修改人
     * 不返回的属性
     */
    @JSONField(serialize = false)
    private Integer updatedBy;

    /**
     * 最后修改时间
     * 返回格式化日期
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdated;

    /**
     * 备注
     */
    private String remarks;

}
