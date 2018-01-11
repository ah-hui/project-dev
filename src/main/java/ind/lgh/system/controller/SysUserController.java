package ind.lgh.system.controller;

import ind.lgh.system.domain.JsonResult;
import ind.lgh.system.domain.SysUser;
import ind.lgh.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SysUserController
 *
 * @author lgh
 * @since 2017-12-21
 */
@RestController
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @RequestMapping(value = "/get/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SysUser get(@PathVariable Integer id){
        return sysUserService.get(id);
    }

}
