package ind.lgh.system.repository;

import ind.lgh.system.domain.SysUser;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 系统用户
 * .@Query(value = "", nativeQuery = true)
 *
 * @author lgh
 */
public interface SysUserRepository extends BaseRepository<SysUser, Integer> {

    SysUser findByLoginName(String loginName);

    SysUser findByLoginNameOrPhone(String loginName, String phone);

    SysUser findById(Integer id);

}
