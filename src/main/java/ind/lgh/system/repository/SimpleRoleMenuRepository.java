package ind.lgh.system.repository;

import ind.lgh.system.domain.SimpleRoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 简单UserRoleMenu权限模型--角色菜单中间表
 *
 * @author lgh
 */
public interface SimpleRoleMenuRepository extends JpaRepository<SimpleRoleMenu, Integer> {

    void deleteByRoleId(Integer roleId);

}
