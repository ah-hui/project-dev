package ind.lgh.system.repository;

import ind.lgh.system.domain.neo4j.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Neo4j实体 PersonRepository
 *
 * @author lgh
 * @since 2018-01-15
 */
@Repository
public interface PersonRepository extends GraphRepository<Person> {

    /**
     * 根据name查询Person实体
     *
     * @param name
     * @return
     */
    Person findByName(@Param("name") String name);

    @Query("MATCH (p:Person) WHERE p.name =~ ('(?i).*'+{name}+'.*') RETURN p")
    Collection<Person> findByNameContaining(@Param("name") String name);

}
