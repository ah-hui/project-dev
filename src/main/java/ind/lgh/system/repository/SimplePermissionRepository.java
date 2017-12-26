package ind.lgh.system.repository;

import ind.lgh.system.domain.SimplePermission;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
