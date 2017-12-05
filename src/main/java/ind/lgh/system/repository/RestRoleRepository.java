package ind.lgh.system.repository;

import ind.lgh.system.domain.RestRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 简单UserRoleMenu权限模型--角色
 * RESTful登录专用
 *
 * @author lgh
 */
public interface RestRoleRepository extends JpaRepository<RestRole, Integer>, JpaSpecificationExecutor<RestRole> {


}
