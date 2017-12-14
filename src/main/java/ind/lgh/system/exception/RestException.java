package ind.lgh.system.exception;

/**
 * 返回JSON格式的异常
 * 返回JSON数据
 *
 * @author lgh
 */
public class RestException extends Exception {

    public RestException(String message) {
        super(message);
    }
}
