package ind.lgh.system.service.impl;

import ind.lgh.system.domain.*;
import ind.lgh.system.domain.neo4j.Message;
import ind.lgh.system.domain.neo4j.Person;
import ind.lgh.system.domain.neo4j.Publish;
import ind.lgh.system.repository.MessageRepository;
import ind.lgh.system.repository.PersonRepository;
import ind.lgh.system.repository.PublishRepository;
import ind.lgh.system.service.IPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Follow Service接口实现
 *
 * @author lgh
 * @since 2018-01-15
 */
@Path("/neo4j/publish")
@Service("publishService")
@Transactional(rollbackFor = Exception.class)
public class PublishServiceImpl implements IPublishService {

    @Autowired
    private PublishRepository publishRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private PersonRepository personRepository;

    @Path("/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public JsonResult post(Publish publish) {
        try {
            Assert.notNull(publish.getPersonId(), "用户信息不完整");
            Assert.notNull(publish.getMessageId(), "用户信息不完整");
            // 先取出完整的Person
            Person person = personRepository.findOne(publish.getPersonId());
            Message message = messageRepository.findOne(publish.getMessageId());
            // 再建立关联
            publish.setPerson(person);
            publish.setMessage(message);
            Publish p = publishRepository.save(publish);
            if (p == null) {
                return JsonResult.createFail("Person发布失败！");
            }
            return JsonResult.createSuccess("Person发布成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.createFail("Person发布失败！" + e.getMessage());
        }
    }
}
