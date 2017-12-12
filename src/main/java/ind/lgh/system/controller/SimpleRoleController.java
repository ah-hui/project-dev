package ind.lgh.system.controller;

import ind.lgh.system.entity.SimpleRole;
import ind.lgh.system.service.SimpleRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 简单UserRoleMenu权限模型--角色
 *
 * @author lgh
 */
@Controller
@RequestMapping("/simple/role")
public class SimpleRoleController extends BaseController {

    @Resource
    SimpleRoleService simpleRoleService;

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
        model.addAttribute("role", role);
        return "/simple/role/roleEdit";
    }

    @RequestMapping("/edit")
    public String edit(SimpleRole role) {
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
