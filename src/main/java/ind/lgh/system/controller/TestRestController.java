package ind.lgh.system.controller;

import ind.lgh.system.domain.SysUser;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 构建RESTful API与单元测试
 * 1.@RestController=@Controller+@ResponseBody
 * 2.以SysSysUser为例，RESTful风格编写API
 * 3.单元测试-当然可以使用Postman之类的插件测试
 *
 * @author lgh
 */
@RestController
@RequestMapping(value = "/rest/test/user")
public class TestRestController {

    /**
     * 创建线程安全的Map---模拟DB-用于观察CRUD对数据的影响
     */
    static Map<Integer, SysUser> users = Collections.synchronizedMap(new HashMap<Integer, SysUser>());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<SysUser> getSysUserList() {
        // 处理"/users/"的GET请求，用来获取用户列表 
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递 
        List<SysUser> r = new ArrayList<>(users.values());
        return r;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postSysUser(@ModelAttribute SysUser user) {
        // 处理"/users/"的POST请求，用来创建SysUser 
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数 
        users.put(user.getId(), user);
        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SysUser getSysUser(@PathVariable Long id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的SysUser信息 
        // url中的id可通过@PathVariable绑定到函数的参数中 
        return users.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putSysUser(@PathVariable Integer id, @ModelAttribute SysUser user) {
        // 处理"/users/{id}"的PUT请求，用来更新SysUser信息 
        SysUser u = users.get(id);
        u.setLoginName(user.getLoginName());
        u.setPhone(user.getPhone());
        users.put(id, u);
        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteSysUser(@PathVariable Long id) {
        // 处理"/users/{id}"的DELETE请求，用来删除SysUser 
        users.remove(id);
        return "success";
    }

}
