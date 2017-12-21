package ind.lgh.system.service;

import ind.lgh.system.domain.SimpleMenu;
import ind.lgh.system.repository.SimpleMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 简单UserRoleMenu权限模型--菜单（权限）
 *
 * @author lgh
 * @since 2017-12-21
 */
@Service("simpleMenuService")
@Transactional
public class SimpleMenuService {

    @Autowired
    private SimpleMenuRepository simpleMenuRepository;

    public SimpleMenu findById(Integer id) {
        return simpleMenuRepository.findById(id);
    }

    public List<SimpleMenu> findAll() {
        return simpleMenuRepository.findAll();
    }

    /**
     * 查询角色对应的全部菜单
     *
     * @param roleId
     * @return
     */
    public List<SimpleMenu> findByRoleId(Integer roleId) {
        return simpleMenuRepository.findByRoleId(roleId);
    }

    /**
     * 新增和修改
     *
     * @param simpleMenu
     * @return
     */
    public SimpleMenu save(SimpleMenu simpleMenu) {
        // 修改时，必须先findOne然后save，因为save时的isNew检查的是version字段而不是id
        SimpleMenu sm = findById(simpleMenu.getId());
        // 修改
        if (sm != null) {
            // set允许用户编辑的字段
            sm.setCode(simpleMenu.getCode());
            sm.setName(simpleMenu.getName());
            sm.setDescription(simpleMenu.getDescription());
            // 保存，高并发下建议用saveAndFlush
            return simpleMenuRepository.saveAndFlush(sm);
        }
        // 新增
        simpleMenu.setDateCreated(new Date());
        simpleMenu.setLastUpdated(new Date());
        // 保存，高并发下建议用saveAndFlush
        return simpleMenuRepository.saveAndFlush(simpleMenu);
    }

    public void delete(Integer id) {
        simpleMenuRepository.delete(id);
    }
}
