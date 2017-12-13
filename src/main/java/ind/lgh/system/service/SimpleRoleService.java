package ind.lgh.system.service;

import ind.lgh.system.domain.SimpleRole;
import ind.lgh.system.repository.SimpleRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 简单UserRoleMenu权限模型--角色
 *
 * @author lgh
 */
@Service("simpleRoleService")
@Transactional
public class SimpleRoleService {

    @Autowired
    private SimpleRoleRepository simpleRoleRepository;

    public SimpleRole findById(Integer id) {
        return simpleRoleRepository.findById(id);
    }

    public List<SimpleRole> findByNameIsLike(String name) {
        return simpleRoleRepository.findByNameIsLike(name);
    }

    public List<SimpleRole> findAll() {
        return simpleRoleRepository.findAll();
    }

    /**
     * 新增和修改
     *
     * @param simpleRole
     * @return
     */
    public SimpleRole save(SimpleRole simpleRole) {
        // 修改时，必须先findOne然后save，因为save时的isNew检查的是version字段而不是id
        SimpleRole sr = findById(simpleRole.getId());
        // set允许用户编辑的字段
        sr.setCode(simpleRole.getCode());
        sr.setName(simpleRole.getName());
        sr.setDescription(simpleRole.getDescription());
        // 保存，高并发下建议用saveAndFlush
        return simpleRoleRepository.saveAndFlush(sr);
    }

    public void delete(Integer id) {
        simpleRoleRepository.delete(id);
    }
}
