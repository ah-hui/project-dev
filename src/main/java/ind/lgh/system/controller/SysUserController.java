package ind.lgh.system.controller;

import ind.lgh.system.entity.SimpleUserRole;
import ind.lgh.system.entity.SysUser;
import ind.lgh.system.service.SimpleUserRoleService;
import ind.lgh.system.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 系统用户
 *
 * @author lgh
 */
@Controller
@RequestMapping("/user")
public class SysUserController extends BaseController {

    @Resource
    SysUserService sysUserService;

    @Resource
    SimpleUserRoleService simpleUserRoleService;

    @RequestMapping("")
    public String index1() {
        return "redirect:/user/list";
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/user/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<SysUser> users = sysUserService.findAll();
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

    @RequestMapping("/add")
    public String add(SysUser user) {
        user.setHashedPassword(user.getPassword());
        sysUserService.save(user);
        return "redirect:/user/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, Integer id) {
        SysUser user = sysUserService.findById(id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(SysUser user) {
        sysUserService.save(user);
        return "redirect:/user/list";
    }

    @RequestMapping("/delete")
    public String delete(Integer id) {
        sysUserService.delete(id);
        return "redirect:/user/list";
    }

    @RequestMapping("/associateRole")
    @ResponseBody
    public void associateRole(HttpServletResponse response, SimpleUserRole userRole) {
        simpleUserRoleService.save(userRole);
        setAjaxMsg(response, true, "关联成功！");
    }

    @RequestMapping("/findUserRoleByUserId")
    @ResponseBody
    public void findUserRoleByUserId(HttpServletResponse response, SimpleUserRole userRole) {
        SimpleUserRole sur = simpleUserRoleService.findByUserId(userRole.getUserId());
        setAjaxMsg(response, true, sur, "查询成功！");
    }

}
