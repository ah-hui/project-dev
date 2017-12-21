package ind.lgh.system.service;

import ind.lgh.system.domain.SimpleUserRole;
import ind.lgh.system.repository.SimpleUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 简单UserRoleUserRole权限模型--用户角色中间表
 * user和role是多对多实现，但实际程序使用起来暂定为多对一
 *
 * @author lgh
 * @since 2017-12-21
 */
@Service("simpleUserRoleService")
@Transactional
public class SimpleUserRoleService {

    @Autowired
    private SimpleUserRoleRepository simpleUserRoleRepository;

    public SimpleUserRole findByUserId(Integer userId) {
        // 暂定user和role的关系是多对一 - 实体类是多对多实现
        List<SimpleUserRole> list = simpleUserRoleRepository.findByUserId(userId);
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
        // 多对一在这里实现 - 先删除全部关联再保存当前关联
        deleteByUserId(sur.getUserId());
        return simpleUserRoleRepository.saveAndFlush(sur);
    }

    /**
     * 删除用户全部关联角色
     *
     * @param userId
     */
    public void deleteByUserId(Integer userId) {
        simpleUserRoleRepository.deleteByUserId(userId);
    }

}
