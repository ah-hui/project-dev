package ind.lgh;

import ind.lgh.system.controller.TestRestController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRestControllerTests {

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new TestRestController()).build();
    }

    @Test
    public void testUserController() throws Exception {
        // 测试UserController
        RequestBuilder request;

        // 1、get查一下user列表，应该为空
        request = get("/rest/test/user/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

        // 2、post提交一个user
        request = post("/rest/test/user/")
                .param("id", "1")
                .param("loginName", "测试大师")
                .param("phone", "130");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        // 3、get获取user列表，应该有刚才插入的数据-!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!测试走到这里会报错，因为实际SysUser字段不止这三个，你懂意思就行了
        request = get("/rest/test/user/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"loginName\":\"测试大师\",\"phone\":130}]")));

        // 4、put修改id为1的user
        request = put("/rest/test/user/1")
                .param("loginName", "测试终极大师")
                .param("phone", "131");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        // 5、get一个id为1的user
        request = get("/rest/test/user/1");
        mvc.perform(request)
                .andExpect(content().string(equalTo("{\"id\":1,\"loginName\":\"测试终极大师\",\"phone\":131}")));

        // 6、del删除id为1的user
        request = delete("/rest/test/user/1");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        // 7、get查一下user列表，应该为空
        request = get("/rest/test/user/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

    }
}
