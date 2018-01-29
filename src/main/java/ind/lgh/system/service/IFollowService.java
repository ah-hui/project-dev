package ind.lgh.system.service;

import ind.lgh.system.domain.neo4j.Follow;
import ind.lgh.system.domain.JsonResult;

/**
 * Follow Service接口
 *
 * @author lgh
 * @since 2018-01-15
 */
public interface IFollowService {

    JsonResult post(Follow follow);
}
