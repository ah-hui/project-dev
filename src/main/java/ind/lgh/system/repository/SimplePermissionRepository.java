package ind.lgh.system.repository;

import ind.lgh.system.domain.SimplePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * RBAC模型--权限
 *
 * @author lgh
 * @since 2017-12-21
 */
public interface SimplePermissionRepository extends JpaRepository<SimplePermission, Integer> {

    SimplePermission findById(Integer id);

    List<SimplePermission> findByResourceType(String resourceType);

    @Query(value =
            "SELECT DISTINCT " +
            "new SimplePermission(sp.id, sp.name, sp.parentId, sp.url) " +
            "FROM " +
            "SimpleUserRole sur, " +
            "SimpleRolePermission srp, " +
            "SimplePermission sp " +
            "WHERE " +
            "sur.roleId = srp.roleId " +
            "AND srp.permissionId = sp.id " +
            "AND sp.resourceType = 'menu' " +
            "AND sur.userId = :userId")
    List<SimplePermission> findMenuByUserId(@Param("userId") Integer userId);

}
