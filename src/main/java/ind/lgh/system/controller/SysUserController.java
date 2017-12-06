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

    @RequestMapping("")
    public String index1() {
        return "redirect:/user/list";
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/user/list";
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
        user.setHashedPassword(user.getPassword());
        userRepository.save(user);
        return "redirect:/user/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model,Integer id){
        SysUser user = userRepository.findOne(id);
        model.addAttribute("user",user);
        return "user/userEdit";
    }

    // 修改时，必须先findOne然后save，因为save时的isNew检查的是version字段而不是id
    @RequestMapping("/edit")
    public String edit(SysUser user){
        SysUser su = userRepository.findOne(user.getId());
        // set允许用户编辑的字段
        su.setLoginName(user.getLoginName());
        su.setPhone(user.getPhone());
        su.setNickName(user.getNickName());
        su.setEmail(user.getEmail());
        // 保存，高并发下建议用saveAndFlush
        userRepository.saveAndFlush(su);
        return "redirect:/user/list";
    }

    @RequestMapping("/delete")
    public String delete(Integer id){
        userRepository.delete(id);
        return "redirect:/user/list";
    }

}
