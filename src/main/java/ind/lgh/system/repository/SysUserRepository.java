package ind.lgh.system.repository;

import ind.lgh.system.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * 系统用户
 * .@Query(value = "", nativeQuery = true)
 *
 * @author lgh
 */
public interface SysUserRepository extends JpaRepository<SysUser, Integer>, QueryDslPredicateExecutor<SysUser> {

    SysUser findByLoginName(String loginName);

    SysUser findByLoginNameOrPhone(String loginName, String phone);

    SysUser findById(Integer id);

}
