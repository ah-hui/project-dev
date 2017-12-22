package ind.lgh.system.controller;

import ind.lgh.system.domain.SimplePermission;
import ind.lgh.system.service.SimplePermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 简单UserRoleMenu权限模型--菜单
 *
 * @author lgh
 * @since 2017-12-21
 */
@Controller
@RequestMapping("/simple/menu")
public class SimplePermissionController extends BaseController {

    @Resource
    SimplePermissionService simplePermissionService;

    @RequestMapping("")
    public String index1() {
        return "redirect:/simple/menu/list";
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/simple/menu/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
//        List<SimplePermission> menus = simplePermissionService.findAll();
//        model.addAttribute("menus", menus);
        return "/simple/menu/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "/simple/menu/menuAdd";
    }

    @RequestMapping("/add")
    public String add(SimplePermission menu) {
//        simplePermissionService.save(menu);
        return "redirect:/simple/menu/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, Integer id) {
//        SimplePermission menu = simplePermissionService.findById(id);
//        model.addAttribute("menu", menu);
        return "/simple/menu/menuEdit";
    }

    @RequestMapping("/edit")
    public String edit(SimplePermission menu) {
//        simplePermissionService.save(menu);
        return "redirect:/simple/menu/list";
    }

    @RequestMapping("/delete")
    public String delete(Integer id) {
//        simplePermissionService.delete(id);
        return "redirect:/simple/menu/list";
    }
}
