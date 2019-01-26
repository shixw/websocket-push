package cc.shixw.wsp.wsp.server;

import cc.shixw.wsp.wsp.api.entity.WSPMessage;
import cc.shixw.wsp.wsp.server.manager.CycleMessageManager;
import cc.shixw.wsp.wsp.server.route.WSPRoute;
import cc.shixw.wsp.wsp.server.timer.CycleWSPTimerTaskImpl;
import io.netty.util.HashedWheelTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: web socket 推送服务
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/26 18:51
 */
public class WebSocketPushServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketPushServer.class);

    private WSPRoute wspRoute;

    private CycleMessageManager cycleMessageManager;

    private HashedWheelTimer timeWheel = new HashedWheelTimer();


    /**
     * 推送一次消息
     * @param wspMessage
     */
    public void pushMessage(WSPMessage wspMessage) {
        try {
            wspRoute.routeMessage(wspMessage);
        } catch (Throwable e) {
            LOGGER.error("消息推送发生异常",e);
        }
    }

    /**
     * 推送循环消息
     * @param wspMessage
     */
    public void pushCycleMessage(WSPMessage wspMessage){
        //将消息放入循环推送消息集合中
        cycleMessageManager.addCycleMessage(wspMessage);
        //推送消息
        executeCycleMessage(wspMessage.getRouteKey(), wspMessage.getMessageId());

    }

    /**
     * 取消循环推送信息
     */
    public void cancelCycleMessage(String routeKey,String messageId){
        cycleMessageManager.removeCycleMessage(routeKey,messageId);
    }

    /**
     * 执行循环消息推送
     */
    public void executeCycleMessage(String routeKey,String messageId){
        WSPMessage wspMessage = cycleMessageManager.getCycleMessageSizeByRouteKeyAndMessageId(routeKey,messageId);
        if (wspMessage==null){
            return;
        }
        //推送一次
        pushMessage(wspMessage);
        //放入到延迟队列中
        timeWheel.newTimeout(new CycleWSPTimerTaskImpl(wspMessage, this),
                wspMessage.getCyclePeriod(), wspMessage.getCycleTimeUnit());
    }


    public WebSocketPushServer() {
    }


    public void setWspRoute(WSPRoute wspRoute) {
        this.wspRoute = wspRoute;
    }

    public void setCycleMessageManager(CycleMessageManager cycleMessageManager) {
        this.cycleMessageManager = cycleMessageManager;
    }
}
