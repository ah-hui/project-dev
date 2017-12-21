package ind.lgh.system.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义系统业务模块异常
 * 返回页面
 *
 * @author lgh
 * @since 2017-12-21
 */
@Getter
@Setter
public class SysException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * 异常类型
     */
    private String eType;

    /**
     * 异常代码
     */
    private String eCode;

    /**
     * 错误信息
     */
    private String message;

    public SysException(String eType, String eCode, String message) {
        super(message);
        this.eType = eType;
        this.eCode = eCode;
        this.message = message;
    }

    public SysException(String message) {
        super(message);
        this.eType = "";
        this.eCode = "";
        this.message = message;
    }

    public SysException() {
    }

}
