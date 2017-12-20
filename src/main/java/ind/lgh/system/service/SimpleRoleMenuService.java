package ind.lgh.system.service;

import ind.lgh.system.domain.SimpleRoleMenu;
import ind.lgh.system.repository.SimpleRoleMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 简单UserRoleMenu权限模型--角色菜单中间表
 *
 * @author lgh
 */
@Service("simpleRoleMenuService")
@Transactional
public class SimpleRoleMenuService {

    @Autowired
    SimpleRoleMenuRepository simpleRoleMenuRepository;

    public void deleteByRoleId(Integer roleId) {
        simpleRoleMenuRepository.deleteByRoleId(roleId);
    }

    public SimpleRoleMenu save(SimpleRoleMenu roleMenu) {
        return simpleRoleMenuRepository.save(roleMenu);
    }

}
