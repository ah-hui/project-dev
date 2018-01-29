package ind.lgh.system.service.impl;

import ind.lgh.system.domain.neo4j.Follow;
import ind.lgh.system.domain.JsonResult;
import ind.lgh.system.domain.neo4j.Person;
import ind.lgh.system.repository.FollowRepository;
import ind.lgh.system.repository.PersonRepository;
import ind.lgh.system.service.IFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

/**
 * Follow Service接口实现
 *
 * @author lgh
 * @since 2018-01-15
 */
@Path("/neo4j/follow")
@Service("followService")
@Transactional(rollbackFor = Exception.class)
public class FollowServiceImpl implements IFollowService {

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private PersonRepository personRepository;

    @Path("/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public JsonResult post(Follow follow) {
        try {
            Assert.notNull(follow.getFollowerId(), "用户信息不完整");
            Assert.notNull(follow.getFollowedId(), "用户信息不完整");
            // 先取出完整的Person
            Optional<Person> optPerson1 = personRepository.findById(follow.getFollowerId());
            Optional<Person> optPerson2 = personRepository.findById(follow.getFollowedId());
            // 再建立关联
            follow.setFollower(optPerson1.orElseThrow(() -> new Exception("Person不存在！id=" + follow.getFollowerId())));
            follow.setFollowed(optPerson2.orElseThrow(() -> new Exception("Person不存在！id=" + follow.getFollowedId())));
            Follow f = followRepository.save(follow);
            if (f == null) {
                return JsonResult.createFail("Person关联失败！");
            }
            return JsonResult.createSuccess("Person关联成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.createFail("Person关联失败！" + e.getMessage());
        }
    }
}
