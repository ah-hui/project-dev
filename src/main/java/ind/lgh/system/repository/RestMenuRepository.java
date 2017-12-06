package ind.lgh.system.repository;

import ind.lgh.system.entity.RestMenu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 简单UserRoleMenu权限模型--菜单
 * RESTful登录专用
 *
 * @author lgh
 */
public interface RestMenuRepository extends JpaRepository<RestMenu, Integer> {
}
