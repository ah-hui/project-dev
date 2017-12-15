package ind.lgh.system.controller;

import ind.lgh.system.exception.RestException;
import ind.lgh.system.exception.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lgh
 */
@Controller
public class HelloWorldController {

    @RequestMapping("/hello")
    public String hello(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        model.addAttribute("name", name);
        return "hello";
    }

    /**
     * 测试统一异常处理
     * 因为这里是处理的异常，所以状态码是200，而不是404
     *
     * @throws Exception
     */
    @RequestMapping("/hello1")
    public void hello1() throws Exception {
        throw new SysException("异常测试-hello1异常！");
    }

    /**
     * 测试REST异常处理
     *
     * @throws Exception
     */
    @RequestMapping("/rest1")
    public void rest1() throws Exception {
        throw new RestException("异常测试-rest1异常！");
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/hello";
    }
}