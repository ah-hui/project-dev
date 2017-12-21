package ind.lgh.system.exception;

/**
 * 返回JSON格式的异常
 * 返回JSON数据
 *
 * @author lgh
 * @since 2017-12-21
 */
public class RestException extends Exception {

    public RestException(String message) {
        super(message);
    }
}
