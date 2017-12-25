package ind.lgh.system.service.impl;

import ind.lgh.system.domain.SimplePermission;
import ind.lgh.system.repository.SimplePermissionRepository;
import ind.lgh.system.service.SimplePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * RBAC模型--权限
 *
 * @author lgh
 * @since 2017-12-21
 */
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
@Service("simplePermissionService")
public class SimplePermissionServiceImpl implements SimplePermissionService{

    @Autowired
    private SimplePermissionRepository simplePermissionRepository;

    @Override
    public List<SimplePermission> findAll() {
        return simplePermissionRepository.findAll();
    }

    @Override
    public SimplePermission findById(Integer id) {
        return simplePermissionRepository.findById(id);
    }

    @Override
    public SimplePermission save(SimplePermission simplePermission) {
        // 修改时，必须先findOne然后save，因为save时的isNew检查的是version字段而不是id
        SimplePermission sp = findById(simplePermission.getId());
        // 修改
        if (sp != null) {
            // set允许用户编辑的字段
            sp.setPermission(simplePermission.getPermission());
            sp.setName(simplePermission.getName());
            sp.setResourceType(simplePermission.getResourceType());
            sp.setUrl(simplePermission.getUrl());
            sp.setParentId(simplePermission.getParentId());
            sp.setParentIds(simplePermission.getParentIds());
            sp.setValid(simplePermission.getValid());
            sp.setDescription(simplePermission.getDescription());
            sp.setLastUpdated(new Date());
            // 保存，高并发下建议用saveAndFlush
            return simplePermissionRepository.saveAndFlush(sp);
        }
        // 新增
        simplePermission.setDateCreated(new Date());
        simplePermission.setLastUpdated(new Date());
        // 保存，高并发下建议用saveAndFlush
        return simplePermissionRepository.saveAndFlush(simplePermission);
    }

    @Override
    public void delete(Integer id) {
        simplePermissionRepository.delete(id);
    }
}
