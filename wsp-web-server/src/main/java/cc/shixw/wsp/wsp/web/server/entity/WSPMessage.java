package cc.shixw.wsp.wsp.web.server.entity;

import java.io.Serializable;

/**
 * @Description:
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/25 23:30
 */
public class WSPMessage<T extends Serializable> implements Serializable{

    /**
     * UUID
     */
    private String uuid;

    /**
     * 路由字段
     */
    private String route;

    /**
     * 消息体
     */
    private T message;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}
