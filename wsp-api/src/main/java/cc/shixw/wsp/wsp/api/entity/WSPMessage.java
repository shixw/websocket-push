package cc.shixw.wsp.wsp.api.entity;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

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
     * 消息ID
     */
    private String messageId;
    /**
     * 消息体
     */
    private String message;
    /**
     * 如果是循环推送的消息 需要传入循环周期
     */
    private long cyclePeriod;

    /**
     * 循环周期时间单位，默认是分钟
     */
    private TimeUnit cycleTimeUnit=TimeUnit.MINUTES;



    public WSPMessage() {
    }

    public WSPMessage(String uuid, String route,String messageId , String message) {
        this.uuid = uuid;
        this.route = route;
        this.messageId = messageId;
        this.message = message;
    }

    public WSPMessage(String uuid, String route, String messageId, String message, long cyclePeriod) {
        this.uuid = uuid;
        this.route = route;
        this.messageId = messageId;
        this.message = message;
        this.cyclePeriod = cyclePeriod;
    }


    public WSPMessage(String uuid, String route , String messageId , String message, long cyclePeriod, TimeUnit cycleTimeUnit) {
        this.uuid = uuid;
        this.route = route;
        this.messageId = messageId;
        this.message = message;
        this.cyclePeriod = cyclePeriod;
        this.cycleTimeUnit = cycleTimeUnit;
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

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getCyclePeriod() {
        return cyclePeriod;
    }

    public void setCyclePeriod(long cyclePeriod) {
        this.cyclePeriod = cyclePeriod;
    }

    public TimeUnit getCycleTimeUnit() {
        return cycleTimeUnit;
    }

    public void setCycleTimeUnit(TimeUnit cycleTimeUnit) {
        this.cycleTimeUnit = cycleTimeUnit;
    }

    @Override
    public String toString() {
        return "WSPMessage{" +
                "uuid='" + uuid + '\'' +
                ", route='" + route + '\'' +
                ", messageId='" + messageId + '\'' +
                ", message='" + message + '\'' +
                ", cyclePeriod=" + cyclePeriod +
                ", cycleTimeUnit=" + cycleTimeUnit +
                '}';
    }

    /**
     * 获取全局路由用的key
     * @return
     */
    public String getRouteKey() {
        return uuid+route;
    }
}
