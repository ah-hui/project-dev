package ind.lgh.system.repository;

import ind.lgh.system.domain.SimpleUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 简单UserRoleMenu权限模型--用户角色中间表
 *
 * @author lgh
 */
public interface SimpleUserRoleRepository extends JpaRepository<SimpleUserRole, Integer> {

    SimpleUserRole findById(Integer id);

    List<SimpleUserRole> findAllByUserId(Integer userId);
}
