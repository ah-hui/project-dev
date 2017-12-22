package ind.lgh.system.service.impl;

import ind.lgh.system.repository.SimplePermissionRepository;
import ind.lgh.system.service.SimplePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RBAC模型--权限
 *
 * @author lgh
 * @since 2017-12-21
 */
@Service("simplePermissionService")
public class SimplePermissionServiceImpl implements SimplePermissionService{

    @Autowired
    private SimplePermissionRepository simplePermissionRepository;

}
