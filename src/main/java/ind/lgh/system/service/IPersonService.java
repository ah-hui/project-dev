package ind.lgh.system.service;

import ind.lgh.system.domain.JsonResult;
import ind.lgh.system.domain.Person;

/**
 * Person Service接口
 *
 * @author lgh
 * @since 2018-01-15
 */
public interface IPersonService {

    JsonResult get(String name);

    JsonResult post(Person person);
}
