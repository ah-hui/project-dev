package ind.lgh.system.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * RestException返回的JSON
 *
 * @author lgh
 * @since 2017-12-21
 */
public class ErrorInfo<T> {

    public static final Integer OK = 0;
    public static final Integer ERROR = 100;

    @Getter
    @Setter
    private Integer code;

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private String url;

    @Getter
    @Setter
    private T data;

    public static Integer getOK() {
        return OK;
    }

    public static Integer getERROR() {
        return ERROR;
    }

}
