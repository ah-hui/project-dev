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

    JsonResult list();

    JsonResult get(Integer id);

    JsonResult post(SysUser user);

    JsonResult put(Integer id, SysUser user);

    JsonResult delete(Integer id, String ip);

    JsonResult listByPage(Integer page, Integer rows);

}
