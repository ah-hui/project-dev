package ind.lgh.system.service;

import ind.lgh.system.domain.JsonResult;
import ind.lgh.system.domain.Message;

/**
 * Message Service接口
 *
 * @author lgh
 * @since 2018-01-15
 */
public interface IMessageService {

    JsonResult get(Long id);

    JsonResult post(Message message);
}
