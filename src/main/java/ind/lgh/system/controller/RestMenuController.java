package ind.lgh.system.controller;

import ind.lgh.system.entity.RestMenu;
import ind.lgh.system.repository.RestMenuRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 简单UserRoleMenu权限模型--菜单
 * RESTful登录专用
 *
 * @author lgh
 */
@Controller
@RequestMapping("/rest/menu")
public class RestMenuController extends BaseController {
    @Resource
    RestMenuRepository restMenuRepository;

    @RequestMapping("")
    public String index1() {
        return "redirect:/rest/menu/list";
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/rest/menu/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<RestMenu> menus = restMenuRepository.findAll();
        model.addAttribute("menus", menus);
        return "/rest/menu/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "/rest/menu/menuAdd";
    }

    @RequestMapping("/add")
    public String add(RestMenu menu) {
        restMenuRepository.save(menu);
        return "redirect:/rest/menu/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, Integer id) {
        RestMenu menu = restMenuRepository.findOne(id);
        model.addAttribute("menu", menu);
        return "/rest/menu/menuEdit";
    }

    // 修改时，必须先findOne然后save，因为save时的isNew检查的是version字段而不是id
    @RequestMapping("/edit")
    public String edit(RestMenu menu) {
        RestMenu rr = restMenuRepository.findOne(menu.getId());
        // set允许用户编辑的字段
        rr.setCode(menu.getCode());
        rr.setName(menu.getName());
        rr.setDescription(menu.getDescription());
        // 保存，高并发下建议用saveAndFlush
        restMenuRepository.saveAndFlush(rr);
        return "redirect:/rest/menu/list";
    }

    @RequestMapping("/delete")
    public String delete(Integer id) {
        restMenuRepository.delete(id);
        return "redirect:/rest/menu/list";
    }
}
