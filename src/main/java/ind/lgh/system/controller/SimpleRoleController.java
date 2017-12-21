package ind.lgh.system.controller;

import ind.lgh.system.domain.SimpleMenu;
import ind.lgh.system.domain.SimpleRole;
import ind.lgh.system.domain.SimpleRoleMenu;
import ind.lgh.system.service.SimpleMenuService;
import ind.lgh.system.service.SimpleRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 简单UserRoleMenu权限模型--角色
 *
 * @author lgh
 * @since 2017-12-21
 */
@Controller
@RequestMapping("/simple/role")
public class SimpleRoleController extends BaseController {

    @Resource
    SimpleRoleService simpleRoleService;

    @Resource
    SimpleMenuService simpleMenuService;

    @RequestMapping("")
    public String index1() {
        return "redirect:/simple/role/list";
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/simple/role/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<SimpleRole> roles = simpleRoleService.findAll();
        model.addAttribute("roles", roles);
        return "/simple/role/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "/simple/role/roleAdd";
    }

    @RequestMapping("/add")
    public String add(SimpleRole role) {
        simpleRoleService.save(role);
        return "redirect:/simple/role/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, Integer id) {
        SimpleRole role = simpleRoleService.findById(id);
        // 查询全部菜单
        List<SimpleMenu> allMenus = simpleMenuService.findAll();
        // 角色对应菜单
        List<SimpleMenu> menus = simpleMenuService.findByRoleId(id);
        model.addAttribute("role", role);
        model.addAttribute("allMenus", allMenus);
        model.addAttribute("menus", menus);
        return "/simple/role/roleEdit";
    }

    @RequestMapping("/edit")
    public String edit(SimpleRole role, Integer[] menus) {
        List<SimpleRoleMenu> list = new ArrayList<>();
        for (int i = 0; i < menus.length; i++) {
            SimpleRoleMenu rm = new SimpleRoleMenu();
            rm.setRoleId(role.getId());
            rm.setMenuId(menus[i]);
            list.add(rm);
        }
        role.setRoleMenus(list);
        simpleRoleService.save(role);
        return "redirect:/simple/role/list";
    }

    @RequestMapping("/delete")
    public String delete(Integer id) {
        simpleRoleService.delete(id);
        return "redirect:/simple/role/list";
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.POST)
    @ResponseBody
    public List<SimpleRole> listAll() {
        List<SimpleRole> roles = simpleRoleService.findAll();
        return roles;
    }

    @RequestMapping(value = "/listNameIsLike", method = RequestMethod.POST)
    @ResponseBody
    public List<SimpleRole> listNameIsLike(String name) {
        List<SimpleRole> roles = simpleRoleService.findByNameIsLike(name + "%");
        return roles;
    }

}
