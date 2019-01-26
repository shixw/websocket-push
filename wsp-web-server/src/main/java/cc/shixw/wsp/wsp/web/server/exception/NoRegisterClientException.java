package cc.shixw.wsp.wsp.web.server.exception;

/**
 * @Description:
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/26 16:35
 */
public class NoRegisterClientException extends RuntimeException {


    public NoRegisterClientException() {
        super();
    }

    public NoRegisterClientException(String message) {
        super(message);

    }

    public NoRegisterClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoRegisterClientException(Throwable cause) {
        super(cause);
    }
}
