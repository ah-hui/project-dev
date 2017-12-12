package ind.lgh.system.repository;

import ind.lgh.system.entity.SimpleRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 简单UserRoleMenu权限模型--角色
 *
 * @author lgh
 */
public interface SimpleRoleRepository extends JpaRepository<SimpleRole, Integer>, JpaSpecificationExecutor<SimpleRole> {

    List<SimpleRole> findByNameIsLike(String name);

    SimpleRole findById(Integer id);

}
