package ind.lgh.system.service;

import ind.lgh.system.domain.SysUser;

import java.util.List;

/**
 * 系统用户
 *
 * @author lgh
 * @since 2017-12-21
 */
public interface SysUserService {

    SysUser findByLoginName(String loginName);

    List<SysUser> findAll();

    SysUser findById(Integer id);

    SysUser save(SysUser user);

    void delete(Integer id);
}
