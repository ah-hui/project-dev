package ind.lgh.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageInterceptor;
import ind.lgh.system.domain.JsonResult;
import ind.lgh.system.domain.SysUser;
import ind.lgh.system.mapper.SysUserMapper;
import ind.lgh.system.service.ISysUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * 用户Service实现
 * RESTful风格：
 * HEAD获取接口是否可用的状态
 * GET用来获取资源
 * POST用来新建资源（也可以用于更新资源）
 * PUT用来更新资源
 * DELETE用来删除资源
 * 响应数据格式@Produces
 * 请求数据格式@Consumes
 * <p>
 * 尚未用到的注解：
 * 1.@QueryParam配合GET食用，取得url的?后面的参数
 * 2.@CookieParam取得cookie里面的参数，比如说sessionId
 * 3.@MatrixParam配合GET食用，取得与分号一起的参数，如queryCity/1;from=wh?name=sh中的form
 * 4.@FormParam取得form表单里的参数，postman里要在body里选中x-www-form-urlencoded
 * 5.@Form标注表单对象，对象中的表单属性需要@FormParam进行标注
 * 6.@DefaultValue配合其他注解使用，标注默认值
 * 7.@Context在filter中使用较多，允许你注入系统级别参数：
 * • javax.ws.rs.core.HttpHeaders
 * • javax.ws.rs.core.UriInfo
 * • javax.ws.rs.core.Request
 * • javax.servlet.http.HttpServletRequest
 * • javax.servlet.http.HttpServletResponse
 * • javax.servlet.ServletConfig
 * • javax.servlet.ServletContext
 * • javax.ws.rs.core.SecurityContext
 * 8.@Encoded配合其他注解使用，标注需要UrlEncode的元素
 *
 * @author lgh
 * @since 2018-01-10
 */
@Path("/user")
@Service("sysUserService")
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl implements ISysUserService {

    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private SysUserMapper userMapper;

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public JsonResult list() {
        List<SysUser> list = userMapper.selectAll();
        JsonResult jr = JsonResult.createSuccess("查询用户成功！");
        jr.addData(list);
        return jr;
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public JsonResult get(@PathParam("id") Integer id) {
        SysUser user = userMapper.selectByPrimaryKey(id);
        JsonResult jr = JsonResult.createSuccess("查询用户成功！");
        jr.addData(user);
        return jr;
    }

    @Path("/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public JsonResult post(SysUser user) {
        int i = userMapper.insert(user);
        if (i < 1) {
            return JsonResult.createFail("新增用户失败！");
        }
        return JsonResult.createSuccess("新增用户成功！");
    }

    @Path("/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public JsonResult put(@PathParam("id") Integer id, SysUser user) {
        user.setId(id);
        int i = userMapper.updateByPrimaryKeySelective(user);
        if (i < 1) {
            return JsonResult.createFail("更新用户失败！");
        }
        return JsonResult.createSuccess("更新用户成功！");
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public JsonResult delete(@PathParam("id") Integer id, @HeaderParam("ip") String ip) {
        int i = userMapper.deleteByPrimaryKey(id);
        logger.info("有个老比在删除! /user/id=" + id + ",跑不了,ip我记下来了: " + ip);
        if (i < 1) {
            return JsonResult.createFail("删除用户失败！");
        }
        return JsonResult.createSuccess("删除用户成功！");
    }

    @Path("/listByPage")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public JsonResult listByPage(@QueryParam("page") Integer page, @QueryParam("rows") Integer rows) {
        page = null == page ? 1 : page;
        rows = null == rows ? 10 : rows;
        // 分页实现原理
        /** 1.设置分页信息到threadLocal中
         * @see com.github.pagehelper.page.PageMethod.LOCAL_PAGE */
        PageHelper.startPage(page, rows);
        /** 2.紧跟着的第一个select方法会被分页，被PageInterceptor截拦,截拦器会从threadLocal中取出分页信息，把分页信息加到sql语句中，实现了分页查询
         * @see com.github.pagehelper.PageInterceptor */
        List<SysUser> list = userMapper.selectAll();
        JsonResult jr = JsonResult.createSuccess("查询用户成功！");
        jr.addData(new PageInfo<>(list));
        return jr;
    }

}
