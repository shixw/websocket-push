package cc.shixw.wsp.wsp.web.server.exception;

/**
 * @Description: 推送内部调用 异常
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/26 16:35
 */
public class WSPInternalPushException extends RuntimeException {


    public WSPInternalPushException() {
        super();
    }

    public WSPInternalPushException(String message) {
        super(message);

    }

    public WSPInternalPushException(String message, Throwable cause) {
        super(message, cause);
    }

    public WSPInternalPushException(Throwable cause) {
        super(cause);
    }
}
