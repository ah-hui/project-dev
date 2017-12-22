package ind.lgh.system.repository;

import ind.lgh.system.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 系统用户
 * 可通过注解@Query(value = "", nativeQuery = true)实现原生sql编写
 * 但JPA不建议使用原生sql或者说就是避免使用原生sql
 *
 * @author lgh
 * @since 2017-12-21
 */
public interface SysUserRepository extends JpaRepository<SysUser, Integer> {

    SysUser findByLoginName(String loginName);

    SysUser findById(Integer id);

}
