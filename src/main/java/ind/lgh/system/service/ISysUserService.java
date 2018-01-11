package ind.lgh.system.service;

import ind.lgh.system.domain.JsonResult;
import ind.lgh.system.domain.SysUser;

/**
 * 用户Service接口
 *
 * @author lgh
 * @since 2018-01-10
 */
public interface ISysUserService {

    JsonResult getOne(Integer id);

    SysUser get(Integer id);
}
