package ind.lgh.system.controller;

import ind.lgh.system.domain.SimplePermission;
import ind.lgh.system.domain.SysUser;
import ind.lgh.system.service.SimplePermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * RBAC模型--权限
 *
 * @author lgh
 * @since 2017-12-21
 */
@Controller
@RequestMapping("/simple/permission")
public class SimplePermissionController extends BaseController {

    @Resource
    SimplePermissionService simplePermissionService;

    @RequestMapping("")
    public String index1() {
        return "redirect:/simple/permission/list";
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/simple/permission/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<SimplePermission> permissions = simplePermissionService.findAll();
        model.addAttribute("permissions", permissions);
        return "/simple/permission/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(Model model) {
        // 查询出全部类型为menu的权限（新增时可以作为父级节点）
        List<SimplePermission> permissions = simplePermissionService.findByResourceType("menu");
        model.addAttribute("permissions", permissions);
        return "/simple/permission/permissionAdd";
    }

    @RequestMapping("/add")
    public String add(SimplePermission permission) {
        simplePermissionService.save(permission);
        return "redirect:/simple/permission/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, Integer id) {
        SimplePermission permission = simplePermissionService.findById(id);
        model.addAttribute("permission", permission);
        return "/simple/permission/permissionEdit";
    }

    @RequestMapping("/edit")
    public String edit(SimplePermission permission) {
        simplePermissionService.save(permission);
        return "redirect:/simple/permission/list";
    }

    @RequestMapping("/delete")
    public String delete(Integer id) {
        simplePermissionService.delete(id);
        return "redirect:/simple/permission/list";
    }


    @RequestMapping(value = "/listMenuByCurrentUser", method = RequestMethod.POST)
    @ResponseBody
    public List<SimplePermission> listMenuByCurrentUser(HttpServletRequest request) {
        List<SimplePermission> menus = null;
        try {
            SysUser currentUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
            menus = simplePermissionService.findMenuByUserId(currentUser.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menus;
    }
}
