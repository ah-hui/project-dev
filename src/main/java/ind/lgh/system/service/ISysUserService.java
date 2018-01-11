package ind.lgh.system.service;

import ind.lgh.system.domain.JsonResult;

/**
 * 用户Service接口
 *
 * @author lgh
 * @since 2018-01-10
 */
public interface ISysUserService {

    JsonResult getOne(Integer id);

    JsonResult get(Integer id);
}
