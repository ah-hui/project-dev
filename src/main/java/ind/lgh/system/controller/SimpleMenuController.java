package ind.lgh.system.controller;

import ind.lgh.system.domain.SimpleMenu;
import ind.lgh.system.service.SimpleMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 简单UserRoleMenu权限模型--菜单
 *
 * @author lgh
 */
@Controller
@RequestMapping("/simple/menu")
public class SimpleMenuController extends BaseController {

    @Resource
    SimpleMenuService simpleMenuService;

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
        List<SimpleMenu> menus = simpleMenuService.findAll();
        model.addAttribute("menus", menus);
        return "/simple/menu/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "/simple/menu/menuAdd";
    }

    @RequestMapping("/add")
    public String add(SimpleMenu menu) {
        simpleMenuService.save(menu);
        return "redirect:/simple/menu/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, Integer id) {
        SimpleMenu menu = simpleMenuService.findById(id);
        model.addAttribute("menu", menu);
        return "/simple/menu/menuEdit";
    }

    @RequestMapping("/edit")
    public String edit(SimpleMenu menu) {
        simpleMenuService.save(menu);
        return "redirect:/simple/menu/list";
    }

    @RequestMapping("/delete")
    public String delete(Integer id) {
        simpleMenuService.delete(id);
        return "redirect:/simple/menu/list";
    }
}
