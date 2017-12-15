package ind.lgh.system.repository;

import ind.lgh.system.domain.SimpleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 简单UserRoleMenu权限模型--菜单
 *
 * @author lgh
 */
public interface SimpleMenuRepository extends JpaRepository<SimpleMenu, Integer> {

    SimpleMenu findById(Integer id);
}