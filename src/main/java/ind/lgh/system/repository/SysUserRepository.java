package ind.lgh.system.repository;

import ind.lgh.system.entity.SysUser;

/**
 * @author lgh
 */
public interface SysUserRepository extends BaseRepository<SysUser, Integer> {

    SysUser findByLoginName(String loginName);

    SysUser findByLoginNameOrPhone(String loginName, String phone);
}
