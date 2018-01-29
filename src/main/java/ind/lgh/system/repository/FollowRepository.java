package ind.lgh.system.repository;

import ind.lgh.system.domain.neo4j.Follow;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * Neo4j关系 FollowRepository
 *
 * @author lgh
 * @since 2018-01-25
 */
@Repository
public interface FollowRepository extends Neo4jRepository<Follow, Long> {
}
