package ind.lgh.system.service;

import ind.lgh.system.domain.SimpleUserRole;
import ind.lgh.system.repository.SimpleUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 简单UserRoleUserRole权限模型--用户角色中间表
 *
 * @author lgh
 */
@Service("simpleUserRoleService")
@Transactional
public class SimpleUserRoleService {

    @Autowired
    private SimpleUserRoleRepository simpleUserRoleRepository;

    public SimpleUserRole findById(Integer id) {
        return simpleUserRoleRepository.findById(id);
    }

    public SimpleUserRole findByUserId(Integer userId) {
        // 目前user和role的关系是一对一，但是考虑以后多对多关系，中间表将不能修改，只能新增
        List<SimpleUserRole> list = simpleUserRoleRepository.findAllByUserId(userId);
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 新增且不能修改
     *
     * @param simpleUserRole
     * @return
     */
    public SimpleUserRole save(SimpleUserRole simpleUserRole) {
        SimpleUserRole sur = new SimpleUserRole();
        sur.setUserId(simpleUserRole.getUserId());
        sur.setRoleId(simpleUserRole.getRoleId());
        if(simpleUserRole.getId() != null){
            delete(simpleUserRole.getId());
        }
        return simpleUserRoleRepository.saveAndFlush(sur);
    }

    public void delete(Integer id){
        simpleUserRoleRepository.delete(id);
    }

}
