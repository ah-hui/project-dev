package ind.lgh.system.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import ind.lgh.system.domain.SimpleRole;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

/**
 * 简单UserRoleMenu权限模型--角色
 *
 * @author lgh
 */
public interface SimpleRoleRepository extends JpaRepository<SimpleRole, Integer>, JpaSpecificationExecutor<SimpleRole>, QueryDslPredicateExecutor<SimpleRole> {

    List<SimpleRole> findByNameIsLike(String name);

    SimpleRole findById(Integer id);


    /**
     * 使用QueryDSL实现超复杂查询
     * http://www.querydsl.com/
     * 多表关联查询：查询出用户和对应的角色
     *
     * @param predicate QueryDSL查询条件
     * @return QueryDSL查询实体
     */
    List<Tuple> findRoleAndMenu(Predicate predicate);

    /**
     * 多表关联查询：带分页
     *
     * @param predicate 查询条件
     * @param pageable  分页
     * @return 分页查询实体
     */
    QueryResults<Tuple> findRoleAndMenuByPage(Predicate predicate, Pageable pageable);

}
