package ind.lgh.system.repository;

import ind.lgh.system.domain.neo4j.Publish;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * Neo4j关系 PublishRepository
 *
 * @author lgh
 * @since 2018-01-25
 */
@Repository
public interface PublishRepository extends Neo4jRepository<Publish, Long> {
}
