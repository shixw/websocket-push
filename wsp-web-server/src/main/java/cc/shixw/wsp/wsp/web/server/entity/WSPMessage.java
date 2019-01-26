package cc.shixw.wsp.wsp.web.server.entity;

import java.io.Serializable;

/**
 * @Description:
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/25 23:30
 */
public class WSPMessage implements Serializable{

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
    private String message;

    public WSPMessage() {
    }

    public WSPMessage(String uuid, String route, String message) {
        this.uuid = uuid;
        this.route = route;
        this.message = message;
    }

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRouteKey(){
        return this.uuid+this.route;
    }
}
