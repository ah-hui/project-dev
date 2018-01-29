package ind.lgh.system.domain.neo4j;

import ind.lgh.system.domain.neo4j.Message;
import ind.lgh.system.domain.neo4j.Person;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.*;

import java.util.Date;

/**
 * Neo4j关系 Publish
 *
 * @author lgh
 * @since 2018-01-25
 */
@RelationshipEntity(type = "PUBLISH")
@Getter
@Setter
public class Publish {

    @Transient
    private Long personId;
    @Transient
    private Long messageId;

    @GraphId
    private Long id;

    @StartNode
    private Person person;

    @EndNode
    private Message message;

    private Date publishDate;

    public Publish() {
    }

    public Publish(Person person, Message message) {
        super();
        this.person = person;
        this.message = message;
    }
}
