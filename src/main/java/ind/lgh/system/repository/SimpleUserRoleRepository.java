package ind.lgh.system.repository;

import ind.lgh.system.domain.SimpleUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * RBAC模型--用户角色中间表
 *
 * @author lgh
 * @since 2017-12-21
 */
public interface SimpleUserRoleRepository extends JpaRepository<SimpleUserRole, Integer>, BaseRepository<SimpleUserRole> {

    List<SimpleUserRole> findByUserId(Integer userId);

    void deleteByUserId(Integer userId);

}
