package ind.lgh.system.service.impl;

import ind.lgh.system.domain.SysUser;
import ind.lgh.system.repository.SysUserRepository;
import ind.lgh.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 系统用户
 *
 * @author lgh
 * @since 2017-12-21
 */
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserRepository userRepository;

    @Override
    public SysUser findByLoginName(String loginName) {
        return userRepository.findByLoginName(loginName);
    }

    @Override
    public List<SysUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public SysUser findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public SysUser save(SysUser user) {
        // 修改时，必须先findOne然后save，因为save时的isNew检查的是version字段而不是id
        SysUser su = null;
        if (user.getId() != null) {
            su = findById(user.getId());
        }
        // 修改
        if (su != null) {
            // set允许用户编辑的字段
            su.setLoginName(user.getLoginName());
            su.setPhone(user.getPhone());
            su.setNickName(user.getNickName());
            su.setEmail(user.getEmail());
            su.setUpdatedBy(-1);
            su.setLastUpdated(new Date());
            // 保存，高并发下建议用saveAndFlush
            return userRepository.saveAndFlush(su);
        }
        // 新增
        user.setCreatedBy(-1);
        user.setDateCreated(new Date());
        user.setUpdatedBy(-1);
        user.setLastUpdated(new Date());
        // 保存，高并发下建议用saveAndFlush
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.delete(id);
    }
}
