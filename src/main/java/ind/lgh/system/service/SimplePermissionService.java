package ind.lgh.system.service;

import ind.lgh.system.domain.SimplePermission;

import java.util.List;

/**
 * RBAC模型--权限
 *
 * @author lgh
 * @since 2017-12-21
 */
public interface SimplePermissionService {

    List<SimplePermission> findAll();

    SimplePermission findById(Integer id);

    List<SimplePermission> findByResourceType(String resourceType);

    SimplePermission save(SimplePermission simplePermission);

    void delete(Integer id);

}
