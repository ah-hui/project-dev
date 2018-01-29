package ind.lgh.system.domain.neo4j;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * Neo4j实体 Message
 *
 * @author lgh
 * @since 2018-01-25
 */
@NodeEntity
@Getter
@Setter
public class Message {

    @GraphId
    private Long id;

    @Property(name = "content")
    private String content;

    public Message() {
    }

    public Message(String content) {
        this.content = content;
    }

}
