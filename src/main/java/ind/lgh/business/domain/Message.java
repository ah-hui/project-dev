package ind.lgh.business.domain;

import ind.lgh.system.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 暂用作测试多数据源 - 消息
 *
 * @author lgh
 * @since 2017-12-21
 */
@Getter
@Setter
@Entity
@Table(name = "message")
public class Message extends BaseEntity {

    /**
     * 消息名称
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * 消息内容
     */
    @Column(name = "content", length = 240, nullable = false)
    private String content;

    public Message() {
    }

    public Message(String name, String content) {
        this.name = name;
        this.content = content;
    }

}
