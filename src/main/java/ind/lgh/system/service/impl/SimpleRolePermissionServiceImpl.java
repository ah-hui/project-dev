package ind.lgh.system.service.impl;

import ind.lgh.system.domain.SimpleRolePermission;
import ind.lgh.system.repository.SimpleRolePermissionRepository;
import ind.lgh.system.service.SimpleRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * RBAC模型--角色权限中间表
 *
 * @author lgh
 * @since 2017-12-25
 */
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
@Service("simpleRolePermissionService")
public class SimpleRolePermissionServiceImpl implements SimpleRolePermissionService {

    @Autowired
    private SimpleRolePermissionRepository simpleRolePermissionRepository;


    @Override
    public List<SimpleRolePermission> findByRoleId(Integer roleId) {
        return simpleRolePermissionRepository.findByRoleId(roleId);
    }

    /**
     * 只能新增删除，不能修改
     *
     * @param list
     * @return
     */
    @Override
    public void batchUpdate(List<SimpleRolePermission> list) {
        // 1.先删除全部该role对应的permission
        deleteByRoleId(list.get(0).getRoleId());
        // 2.保存新的对应关系
        simpleRolePermissionRepository.batchInsert(list);
    }

    @Override
    public void deleteByRoleId(Integer roleId) {
        simpleRolePermissionRepository.deleteByRoleId(roleId);
    }
}
