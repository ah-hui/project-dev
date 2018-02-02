package ind.lgh.system.service.impl;

import ind.lgh.system.domain.JsonResult;
import ind.lgh.system.domain.neo4j.Person;
import ind.lgh.system.repository.PersonRepository;
import ind.lgh.system.service.IPersonService;
import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver;
import org.neo4j.ogm.service.Components;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.sql.*;

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

    @Path("/testJdbc")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public JsonResult testJdbc() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:neo4j:http://localhost:7474", "neo4j", "root");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("MATCH (n)-[r]-(g) RETURN n,r,g");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("g"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return JsonResult.createSuccess("Jdbc连接失败！");
        }
        return JsonResult.createSuccess("Jdbc连接成功！");
    }

    @Path("/testEmbedded")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public JsonResult testEmbedded() {
        try {
            GraphDatabaseFactory databaseFactory = new GraphDatabaseFactory();
            GraphDatabaseService graphDatabaseService = databaseFactory.newEmbeddedDatabase(new File("D:/embedded-neo4j/"));

            Transaction tx = graphDatabaseService.beginTx();

            Node node1 = graphDatabaseService.createNode(new NodeLabel("Book"));
            node1.setProperty("name", "三国演义");

            Node node2 = graphDatabaseService.createNode(new NodeLabel("Author"));
            node2.setProperty("name", "罗贯中");

            Relationship rs = node2.createRelationshipTo(node1, new RsType("Write"));
            rs.setProperty("cName", "写作");

            tx.success();

            graphDatabaseService.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.createSuccess("Embedded连接失败！");
        } finally {
            System.out.println("处理成功！");
        }
        return JsonResult.createSuccess("Embedded连接成功！");
    }

    public static void main(String[] args) {
        new PersonServiceImpl().testEmbedded();
    }

    class RsType implements RelationshipType {

        private String type;

        public RsType(String type) {
            this.type = type;
        }

        @Override
        public String name() {
            return type;
        }
    }

    class NodeLabel implements Label {

        private String label;

        public NodeLabel(String label) {
            this.label = label;
        }

        @Override
        public String name() {
            return label;
        }
    }

}
