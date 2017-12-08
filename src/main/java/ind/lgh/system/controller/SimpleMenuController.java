package ind.lgh.system.controller;

import ind.lgh.system.entity.SimpleMenu;
import ind.lgh.system.repository.SimpleMenuRepository;
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
    SimpleMenuRepository simpleMenuRepository;

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
        List<SimpleMenu> menus = simpleMenuRepository.findAll();
        model.addAttribute("menus", menus);
        return "/simple/menu/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "/simple/menu/menuAdd";
    }

    @RequestMapping("/add")
    public String add(SimpleMenu menu) {
        simpleMenuRepository.save(menu);
        return "redirect:/simple/menu/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, Integer id) {
        SimpleMenu menu = simpleMenuRepository.findOne(id);
        model.addAttribute("menu", menu);
        return "/simple/menu/menuEdit";
    }

    // 修改时，必须先findOne然后save，因为save时的isNew检查的是version字段而不是id
    @RequestMapping("/edit")
    public String edit(SimpleMenu menu) {
        SimpleMenu rr = simpleMenuRepository.findOne(menu.getId());
        // set允许用户编辑的字段
        rr.setCode(menu.getCode());
        rr.setName(menu.getName());
        rr.setDescription(menu.getDescription());
        // 保存，高并发下建议用saveAndFlush
        simpleMenuRepository.saveAndFlush(rr);
        return "redirect:/simple/menu/list";
    }

    @RequestMapping("/delete")
    public String delete(Integer id) {
        simpleMenuRepository.delete(id);
        return "redirect:/simple/menu/list";
    }
}
