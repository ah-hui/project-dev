package ind.lgh.system.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * JSON 返回结果类
 * REST接口统一返回格式
 *
 * @author lgh
 * @since 2017-12-21
 */
public class JsonResult {
    /**
     * 响应状态：操作成功
     */
    public static final Integer JSON_RESULT_SUCCESS = 1;

    /**
     * 响应状态：操作失败
     */
    public static final Integer JSON_RESULT_FAIL = 0;

    /**
     * 响应状态：部分成功
     */
    public static final Integer JSON_RESULT_SUCCESS_PART = 2;

    /**
     * 返回数据
     */
    @Getter
    private final List<Object> data = new ArrayList<>();

    /**
     * 状态
     */
    @Getter
    @Setter
    private Integer returnCode;

    /**
     * 信息描述
     */
    @Getter
    @Setter
    private String msg;

    /**
     * 响应的HTML代码
     */
    @Getter
    @Setter
    private String html;

    protected JsonResult() {
    }

    private JsonResult(final Integer returnCode, final String msg) {
        super();
        this.returnCode = returnCode;
        this.msg = msg;
    }

    /**
     * 创建成功状态的JsonResult
     */
    public static JsonResult createSuccess() {
        return new JsonResult(JsonResult.JSON_RESULT_SUCCESS, null);
    }

    /**
     * 创建成功状态的JsonResult
     */
    public static JsonResult createSuccess(final String msg) {
        return new JsonResult(JsonResult.JSON_RESULT_SUCCESS, msg);
    }

    /**
     * 创建部分成功状态的JsonResult
     */
    public static JsonResult createSuccessPart() {
        return new JsonResult(JsonResult.JSON_RESULT_SUCCESS_PART, null);
    }

    /**
     * 创建部分成功状态的JsonResult
     */
    public static JsonResult createSuccessPart(final String msg) {
        return new JsonResult(JsonResult.JSON_RESULT_SUCCESS_PART, msg);
    }

    /**
     * 创建失败状态的JsonResult
     */
    public static JsonResult createFail() {
        return new JsonResult(JsonResult.JSON_RESULT_FAIL, null);
    }

    /**
     * 创建失败状态的JsonResult
     */
    public static JsonResult createFail(final String msg) {
        return new JsonResult(JsonResult.JSON_RESULT_FAIL, msg);
    }

    /**
     * 添加返回数据
     */
    public void addData(final Object object) {
        data.add(object);
    }

    /**
     * 清空返回数据
     */
    public void clearData() {
        if (null != data) {
            data.clear();
        }
    }
}
