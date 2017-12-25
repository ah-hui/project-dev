package ind.lgh.system.service;

import ind.lgh.system.domain.SimpleRolePermission;

import java.util.List;

/**
 * RBAC模型--角色权限中间表
 *
 * @author lgh
 * @since 2017-12-21
 */
public interface SimpleRolePermissionService {
    
    List<SimpleRolePermission> findByRoleId(Integer userId);

    void batchUpdate(List<SimpleRolePermission> list);

    void deleteByRoleId(Integer userId);
}
