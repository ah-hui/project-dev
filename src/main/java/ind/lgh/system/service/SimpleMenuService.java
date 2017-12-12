package ind.lgh.system.service;

import ind.lgh.system.entity.SimpleMenu;
import ind.lgh.system.repository.SimpleMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 简单UserRoleMenu权限模型--菜单（权限）
 *
 * @author lgh
 */
@Service("simpleMenuService")
@Transactional
public class SimpleMenuService {

    @Autowired
    private SimpleMenuRepository simpleMenuRepository;

    public SimpleMenu findById(Integer id){
        return simpleMenuRepository.findById(id);
    }

    public List<SimpleMenu> findAll(){
        return simpleMenuRepository.findAll();
    }

    /**
     * 新增和修改
     * @param simpleMenu
     * @return
     */
    public SimpleMenu save(SimpleMenu simpleMenu){
        // 修改时，必须先findOne然后save，因为save时的isNew检查的是version字段而不是id
        SimpleMenu sm = findById(simpleMenu.getId());
        // set允许用户编辑的字段
        sm.setCode(simpleMenu.getCode());
        sm.setName(simpleMenu.getName());
        sm.setDescription(simpleMenu.getDescription());
        // 保存，高并发下建议用saveAndFlush
        return simpleMenuRepository.saveAndFlush(sm);
    }

    public void delete(Integer id){
        simpleMenuRepository.delete(id);
    }
}
