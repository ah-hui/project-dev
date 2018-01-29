package ind.lgh.system.service.impl;

import ind.lgh.system.domain.JsonResult;
import ind.lgh.system.domain.neo4j.Message;
import ind.lgh.system.repository.MessageRepository;
import ind.lgh.system.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

/**
 * Message Service接口实现
 *
 * @author lgh
 * @since 2018-01-15
 */
@Path("/neo4j/message")
@Service("messageService")
@Transactional(rollbackFor = Exception.class)
public class MessageServiceImpl implements IMessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public JsonResult get(@PathParam("id") Long id) {
        Optional<Message> optMessage = messageRepository.findById(id);
        JsonResult jr = JsonResult.createSuccess("查询Message成功！");
        jr.addData(optMessage.orElse(null));
        return jr;
    }

    @Path("/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public JsonResult post(Message message) {
        Message m = messageRepository.save(message);
        if (m == null) {
            return JsonResult.createFail("新增Message失败！");
        }
        JsonResult jr = JsonResult.createSuccess("新增Message成功！");
        jr.addData(m);
        return jr;
    }
}
