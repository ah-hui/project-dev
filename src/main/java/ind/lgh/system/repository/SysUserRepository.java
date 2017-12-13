package ind.lgh.system.repository;

import ind.lgh.system.domain.SysUser;

/**
 * 系统用户
 *
 * @author lgh
 */
public interface SysUserRepository extends BaseRepository<SysUser, Integer> {

    SysUser findByLoginName(String loginName);

    SysUser findByLoginNameOrPhone(String loginName, String phone);

    SysUser findById(Integer id);
}
