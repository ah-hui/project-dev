package ind.lgh.system.service.impl;

import ind.lgh.system.domain.SimpleUserRole;
import ind.lgh.system.repository.SimpleUserRoleRepository;
import ind.lgh.system.service.SimpleUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * RBAC模型--用户角色中间表
 *
 * @author lgh
 * @since 2017-12-25
 */
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
@Service("simpleUserRoleService")
public class SimpleUserRoleServiceImpl implements SimpleUserRoleService {

    @Autowired
    private SimpleUserRoleRepository simpleUserRoleRepository;


    @Override
    public List<SimpleUserRole> findByUserId(Integer userId) {
        return simpleUserRoleRepository.findByUserId(userId);
    }

    /**
     * 只能新增删除，不能修改
     *
     * @param list
     * @return
     */
    @Override
    public void batchUpdate(List<SimpleUserRole> list) {
        // 1.先删除全部该user对应的role
        deleteByUserId(list.get(0).getUserId());
        // 2.保存新的对应关系
        simpleUserRoleRepository.batchInsert(list);
    }

    @Override
    public void deleteByUserId(Integer userId) {
        simpleUserRoleRepository.deleteByUserId(userId);
    }
}
