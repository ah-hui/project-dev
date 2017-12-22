package ind.lgh.system.repository;

import ind.lgh.system.domain.SimpleRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * RBAC模型--角色
 *
 * @author lgh
 * @since 2017-12-21
 */
public interface SimpleRoleRepository extends JpaRepository<SimpleRole, Integer>, JpaSpecificationExecutor<SimpleRole> {

}
