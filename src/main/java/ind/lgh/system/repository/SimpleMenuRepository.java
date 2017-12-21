package ind.lgh.system.repository;

import ind.lgh.system.domain.SimpleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 简单UserRoleMenu权限模型--菜单
 *
 * @author lgh
 * @since 2017-12-21
 */
public interface SimpleMenuRepository extends JpaRepository<SimpleMenu, Integer> {

    SimpleMenu findById(Integer id);

    @Query(value = "SELECT RM.MENU_ID AS ID FROM SIMPLE_ROLE_MENU RM " +
            "WHERE RM.ROLE_ID = :roleId", nativeQuery = true)
    List<SimpleMenu> findByRoleId(@Param("roleId") Integer roleId);
}
