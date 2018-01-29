package ind.lgh.system.domain.neo4j;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.*;

import java.util.Date;

/**
 * Neo4j关系 Follow
 *
 * @author lgh
 * @since 2018-01-25
 */
@RelationshipEntity(type = "FOLLOW")
@Getter
@Setter
public class Follow {

    @Transient
    private Long followerId;
    @Transient
    private Long followedId;

    @GraphId
    private Long id;

    /**
     * 关系的起始节点
     */
    @StartNode
    private Person follower;

    /**
     * 关系的终止节点
     */
    @EndNode
    private Person followed;

    @Setter
    private Date followingDate;

    public Follow() {
    }

    public Follow(Person follower, Person followed) {
        super();
        this.follower = follower;
        this.followed = followed;
    }
}
