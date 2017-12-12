package ind.lgh.system.service;

import ind.lgh.system.entity.SysUser;
import ind.lgh.system.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统用户
 *
 * @author lgh
 */
@Service("sysUserService")
@Transactional
public class SysUserService {

    @Autowired
    private SysUserRepository userRepository;

    public SysUser findById(Integer id) {
        return userRepository.findById(id);
    }

    public List<SysUser> findAll() {
        return userRepository.findAll();
    }

    /**
     * 新增和修改
     *
     * @param user
     * @return
     */
    public SysUser save(SysUser user) {
        // 修改时，必须先findOne然后save，因为save时的isNew检查的是version字段而不是id
        SysUser su = findById(user.getId());
        // set允许用户编辑的字段
        su.setLoginName(user.getLoginName());
        su.setPhone(user.getPhone());
        su.setNickName(user.getNickName());
        su.setEmail(user.getEmail());
        // 保存，高并发下建议用saveAndFlush
        return userRepository.saveAndFlush(su);
    }

    public void delete(Integer id) {
        userRepository.delete(id);
    }

}
