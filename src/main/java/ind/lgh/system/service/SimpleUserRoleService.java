package ind.lgh.system.service;

import ind.lgh.system.domain.SimpleUserRole;

import java.util.List;

/**
 * RBAC模型--用户角色中间表
 *
 * @author lgh
 * @since 2017-12-21
 */
public interface SimpleUserRoleService {

    List<SimpleUserRole> findByUserId(Integer userId);

    void batchUpdate(List<SimpleUserRole> list);

    void deleteByUserId(Integer userId);

}
