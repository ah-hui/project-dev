package ind.lgh.system.controller;

import ind.lgh.system.config.shiro.PasswordManager;
import ind.lgh.system.domain.SimpleUserRole;
import ind.lgh.system.domain.SysUser;
import ind.lgh.system.service.SimpleUserRoleService;
import ind.lgh.system.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
 * @since 2017-12-21
 */
@Controller
@RequestMapping("/user")
public class SysUserController extends BaseController {

    @Resource
    SysUserService sysUserService;

//    @Resource
//    SimpleUserRoleService simpleUserRoleService;

    @RequestMapping("")
    public String index1() {
        return "redirect:/user/list";
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/user/list";
    }

    @RequestMapping("/list")
    @RequiresPermissions("user:view")
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
    @RequiresPermissions("user:create")
    public String add(SysUser user) {
        user.setHashedPassword(PasswordManager.encode(user.getPassword(),user.getLoginName()));
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
    @RequiresPermissions("user:update")
    public String edit(SysUser user) {
        sysUserService.save(user);
        return "redirect:/user/list";
    }

    @RequestMapping("/delete")
    @RequiresPermissions("user:delete")
    public String delete(Integer id) {
        sysUserService.delete(id);
        return "redirect:/user/list";
    }

    @RequestMapping("/associateRole")
    @ResponseBody
    public void associateRole(HttpServletResponse response, SimpleUserRole userRole) {
//        simpleUserRoleService.save(userRole);
        setAjaxMsg(response, true, "关联成功！");
    }

}
