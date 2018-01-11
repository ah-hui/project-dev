package ind.lgh.system.service.impl;

import ind.lgh.system.domain.JsonResult;
import ind.lgh.system.domain.SysUser;
import ind.lgh.system.mapper.SysUserMapper;
import ind.lgh.system.service.ISysUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * 用户Service实现
 *
 * @author lgh
 * @since 2018-01-10
 */
@Path("user")
@Service("sysUserService")
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer")
    @Path("{id}")
    @GET
    /* 输出json */
    @Produces(MediaType.APPLICATION_JSON)
    /* 接收json */
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public JsonResult getOne(@PathParam("id") Integer id) {
        SysUser user =  userMapper.selectByPrimaryKey(id);
        JsonResult jr = JsonResult.createSuccess("查询用户成功！");
        jr.addData(user);
        return jr;
    }

    /**
     * controller专用service
     */
    @Override
    public JsonResult get(Integer id) {
        SysUser user =  userMapper.selectByPrimaryKey(id);
        JsonResult jr = JsonResult.createSuccess("查询用户成功！");
        jr.addData(user);
        return jr;
    }
}
