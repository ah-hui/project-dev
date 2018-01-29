package ind.lgh.system.service;

import ind.lgh.system.domain.JsonResult;
import ind.lgh.system.domain.neo4j.Publish;

/**
 * Publish Service接口
 *
 * @author lgh
 * @since 2018-01-15
 */
public interface IPublishService {

    JsonResult post(Publish publish);
}
