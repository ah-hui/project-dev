package ind.lgh.system.service;

import ind.lgh.system.domain.SimpleRole;

import java.util.List;

/**
 * RBAC模型--角色
 *
 * @author lgh
 * @since 2017-12-21
 */
public interface SimpleRoleService {

    List<SimpleRole> findAll();

    SimpleRole findById(Integer id);

    SimpleRole save(SimpleRole simpleRole);

    void delete(Integer id);

}
