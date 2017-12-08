package ind.lgh.system.controller;

import ind.lgh.system.entity.SimpleRole;
import ind.lgh.system.repository.SimpleRoleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
    SimpleRoleRepository simpleRoleRepository;

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
        List<SimpleRole> roles = simpleRoleRepository.findAll();
        model.addAttribute("roles", roles);
        return "/simple/role/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "/simple/role/roleAdd";
    }

    @RequestMapping("/add")
    public String add(SimpleRole role) {
        simpleRoleRepository.save(role);
        return "redirect:/simple/role/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, Integer id) {
        SimpleRole role = simpleRoleRepository.findOne(id);
        model.addAttribute("role", role);
        return "/simple/role/roleEdit";
    }

    // 修改时，必须先findOne然后save，因为save时的isNew检查的是version字段而不是id
    @RequestMapping("/edit")
    public String edit(SimpleRole role) {
        SimpleRole rr = simpleRoleRepository.findOne(role.getId());
        // set允许用户编辑的字段
        rr.setCode(role.getCode());
        rr.setName(role.getName());
        rr.setDescription(role.getDescription());
        // 保存，高并发下建议用saveAndFlush
        simpleRoleRepository.saveAndFlush(rr);
        return "redirect:/simple/role/list";
    }

    @RequestMapping("/delete")
    public String delete(Integer id) {
        simpleRoleRepository.delete(id);
        return "redirect:/simple/role/list";
    }

}
