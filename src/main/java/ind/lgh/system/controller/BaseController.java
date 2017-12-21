package ind.lgh.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 本系统中接口返回书写方式
 * 保存 删除 修改 应该用 super.setAjaxMsg( response, true, "删除成功" );  这种方式。
 * 详情查询应改使用：super.setFormJson( response, true, departmentVO, "成功" ); 这种方式。
 * 列表展示应该用： super.setGridJson( response, byCondition.size(), byCondition );
 * 分页展示应该用：super.setGridJson( response, byPage );
 *
 * @author lgh
 * @since 2017-12-21
 */
public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 返回Ajax提示信息 多用于增加、删除、修改时的操作提示
     *
     * @param response the response
     * @param boo      the boo
     * @param msg      the msg
     * @throws IOException
     */
    public void setAjaxMsg(HttpServletResponse response, boolean boo, String msg) {
        try {

            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("{\"success\": " + boo + ", \"msg\": \"" + msg + "\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将实体类封装为json对象返回给前端
     *
     * @param response the response
     * @param boo      the boo
     * @param obj      the form 对象
     * @param msg      the 消息
     */
    public void setAjaxMsg(HttpServletResponse response, boolean boo, Object obj, String msg) {
        StringBuffer jsonStrBuf = new StringBuffer();
        if (msg == null) {
            msg = "";
        }
        if (!boo) {
            jsonStrBuf.append("{\"success\": false, \"msg\": ").append("\"" + msg).append("\"}");
        } else {
            jsonStrBuf.append("{\"success\": true, \"data\":").append(JSONObject.toJSONString(obj)).append(",\"msg\":").append("\"" + msg).append("\"}");
        }
        try {
            response.setContentType("text/html;charset=utf-8");
            String string = jsonStrBuf.toString();
            response.getWriter().print(JSONObject.parseObject(string));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 以列表形式展示的数据不具备分页的以json格式此返回
     *
     * @param response the response
     * @param total    the 总行数
     * @param list     the 数据集
     */
    public void setGridJson(HttpServletResponse response, int total, List<?> list) {
        StringBuffer jsonStrBuf = new StringBuffer();
        jsonStrBuf.append("{\"total\":").append(total).append(",");
        try {
            jsonStrBuf.append("'success': ").append(true).append(",");
            jsonStrBuf.append("\"data\":").append(JSONArray.toJSONString(list));
        } catch (Exception e) {
            e.printStackTrace();
            jsonStrBuf.append("'success': false,");
            jsonStrBuf.append("'msg': '数据转换出错',");
        }

        jsonStrBuf.append("}");

        try {
            System.out.println(jsonStrBuf);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(JSONObject.parseObject(jsonStrBuf.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 以表格形式展示并且具备分页的以json格式返回
     *
     * @param response the response
     * @param page     the page
     */
    public void setGridJson(HttpServletResponse response, Page<?> page) {
        StringBuffer jsonStrBuf = new StringBuffer();
        try {
            jsonStrBuf.append("{\"success\":").append(true).append(",");
            jsonStrBuf.append("\"data\":").append(JSONArray.toJSONString(page));
        } catch (Exception e) {
            e.printStackTrace();
            jsonStrBuf.append("'success': false,");
            jsonStrBuf.append("'msg': '数据转换出错',");
        }

        jsonStrBuf.append("}");
        try {
            System.out.println(jsonStrBuf);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(JSONObject.parseObject(jsonStrBuf.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get root path string.
     * 返回web-info 的位置
     *
     * @return the string
     */
    public String getRootPath() {
        return new File(BaseController.class.getClassLoader().getResource("").getPath() + "/").getParentFile().getPath() + "/";
    }

}
