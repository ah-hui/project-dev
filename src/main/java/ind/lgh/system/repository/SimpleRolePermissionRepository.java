package ind.lgh.system.repository;

import ind.lgh.system.domain.SimpleRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RBAC模型--角色权限中间表
 *
 * @author lgh
 * @since 2017-12-21
 */
public interface SimpleRolePermissionRepository extends JpaRepository<SimpleRolePermission, Integer> {

}
