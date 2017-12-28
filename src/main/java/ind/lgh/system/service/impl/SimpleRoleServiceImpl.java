package ind.lgh.system.service.impl;

import ind.lgh.system.domain.SimpleRole;
import ind.lgh.system.domain.SimpleRolePermission;
import ind.lgh.system.repository.SimpleRoleRepository;
import ind.lgh.system.service.SimpleRolePermissionService;
import ind.lgh.system.service.SimpleRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * RBAC模型--角色
 *
 * @author lgh
 * @since 2017-12-22
 */
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
@Service("simpleRoleService")
public class SimpleRoleServiceImpl implements SimpleRoleService {

    @Autowired
    private SimpleRoleRepository simpleRoleRepository;

    @Resource
    private SimpleRolePermissionService simpleRolePermissionService;

    @Override
    public List<SimpleRole> findAll() {
        return simpleRoleRepository.findAll();
    }

    @Override
    public SimpleRole findById(Integer id) {
        return simpleRoleRepository.findById(id);
    }

    @Override
    public SimpleRole save(SimpleRole simpleRole) {
        // 修改时，必须先findOne然后save，因为save时的isNew检查的是version字段而不是id
        SimpleRole sr = null;
        if (simpleRole.getId() != null) {
            sr = findById(simpleRole.getId());
        }
        SimpleRole result;
        // 1.1.role修改
        if (sr != null) {
            // set允许用户编辑的字段
            sr.setName(simpleRole.getName());
            sr.setCode(simpleRole.getCode());
            sr.setDescription(simpleRole.getDescription());
            sr.setUpdatedBy(-1);
            sr.setLastUpdated(new Date());
            // 保存，高并发下建议用saveAndFlush
            result = simpleRoleRepository.saveAndFlush(sr);
        } else {
            // 1.2.role新增
            simpleRole.setCreatedBy(-1);
            simpleRole.setDateCreated(new Date());
            simpleRole.setUpdatedBy(-1);
            simpleRole.setLastUpdated(new Date());
            // 保存，高并发下建议用saveAndFlush
            result = simpleRoleRepository.saveAndFlush(simpleRole);
        }
        // 2.保存菜单对应权限 - 先删除角色对应的全部菜单，再批量新增
        List<SimpleRolePermission> list = simpleRole.getRolePermissions();
        if (list != null && list.size() > 0) {
            simpleRolePermissionService.batchUpdate(list);
        }
        return result;
    }

    @Override
    public void delete(Integer id) {
        simpleRoleRepository.delete(id);
    }
}
