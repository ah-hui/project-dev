package ind.lgh.system.service.impl;

import ind.lgh.system.domain.JsonResult;
import ind.lgh.system.domain.Person;
import ind.lgh.system.repository.PersonRepository;
import ind.lgh.system.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Person Service接口实现
 *
 * @author lgh
 * @since 2018-01-15
 */
@Path("/neo4j/person")
@Service("personService")
@Transactional(rollbackFor = Exception.class)
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private PersonRepository personRepository;

    @Path("/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public JsonResult get(@PathParam("name") String name) {
        Person person = personRepository.findByName(name);
        JsonResult jr = JsonResult.createSuccess("查询Person成功！");
        jr.addData(person);
        return jr;
    }

    @Path("/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public JsonResult post(Person person) {
        Person p = personRepository.save(person);
        if (p == null) {
            JsonResult jr = JsonResult.createFail("新增Person失败！");
            jr.addData(p);
            return jr;
        }
        JsonResult jr = JsonResult.createSuccess("新增Person成功！");
        jr.addData(p);
        return jr;
    }
}
