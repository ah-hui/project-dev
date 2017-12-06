package ind.lgh.system.controller;

import ind.lgh.system.entity.SysUser;
import ind.lgh.system.repository.SysUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 暂时直接调用Repository不引入Service层
 *
 * @author lgh
 */
@Controller
@RequestMapping("/user")
public class SysUserController extends BaseController {

    @Resource
    SysUserRepository userRepository;

    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<SysUser> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

    @RequestMapping("/add")
    public String add(SysUser user) {
        userRepository.save(user);
        return "redirect:/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model,Integer id){
        SysUser user = userRepository.findOne(id);
        model.addAttribute("user",user);
        return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(SysUser user){
        userRepository.save(user);
        return "redirect:/list";
    }

    @RequestMapping("/delete")
    public String delete(Integer id){
        userRepository.delete(id);
        return "redirect:/list";
    }

}
