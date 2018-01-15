package ind.lgh.system.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Neo4j实体 Person
 *
 * @author lgh
 * @since 2018-01-15
 */
@NodeEntity
@Getter
@Setter
@ToString
public class Person {

    @GraphId
    private Long id;

    private String name;

}
