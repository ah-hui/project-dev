package ind.lgh.system.service;

import ind.lgh.system.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lgh
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long> {

    SysUser findByLoginName(String loginName);

    SysUser findByLoginNameOrPhone(String loginName, String phone);
}
