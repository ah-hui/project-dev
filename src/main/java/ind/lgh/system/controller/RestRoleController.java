package ind.lgh.system.controller;

import ind.lgh.system.entity.RestRole;
import ind.lgh.system.repository.RestRoleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 简单UserRoleMenu权限模型--角色
 * RESTful登录专用
 *
 * @author lgh
 */
@Controller
@RequestMapping("/rest/role")
public class RestRoleController extends BaseController {

    @Resource
    RestRoleRepository restRoleRepository;

    @RequestMapping("")
    public String index1() {
        return "redirect:/rest/role/list";
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/rest/role/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<RestRole> roles = restRoleRepository.findAll();
        model.addAttribute("roles", roles);
        return "/rest/role/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "/rest/role/roleAdd";
    }

    @RequestMapping("/add")
    public String add(RestRole role) {
        restRoleRepository.save(role);
        return "redirect:/rest/role/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, Integer id) {
        RestRole role = restRoleRepository.findOne(id);
        model.addAttribute("role", role);
        return "/rest/role/roleEdit";
    }

    // 修改时，必须先findOne然后save，因为save时的isNew检查的是version字段而不是id
    @RequestMapping("/edit")
    public String edit(RestRole role) {
        RestRole rr = restRoleRepository.findOne(role.getId());
        // set允许用户编辑的字段
        rr.setCode(role.getCode());
        rr.setName(role.getName());
        rr.setDescription(role.getDescription());
        // 保存，高并发下建议用saveAndFlush
        restRoleRepository.saveAndFlush(rr);
        return "redirect:/rest/role/list";
    }

    @RequestMapping("/delete")
    public String delete(Integer id) {
        restRoleRepository.delete(id);
        return "redirect:/rest/role/list";
    }

}
