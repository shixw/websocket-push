package cc.shixw.wsp.wsp.server;

import cc.shixw.wsp.wsp.api.entity.WSPMessage;
import cc.shixw.wsp.wsp.server.kafka.KafkaWSPMessageProducer;
import cc.shixw.wsp.wsp.server.manager.CycleMessageManager;
import cc.shixw.wsp.wsp.server.route.WSPRoute;
import cc.shixw.wsp.wsp.server.timer.CycleWSPTimerTaskImpl;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * @Description: web socket 推送服务
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/26 18:51
 */
public class WebSocketPushServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketPushServer.class);

    private WSPRoute wspRoute;

    private CycleMessageManager cycleMessageManager;

    private KafkaWSPMessageProducer kafkaWSPMessageProducer;

    private HashedWheelTimer timeWheel = new HashedWheelTimer();


    public void init(){

    }

    public void stop(){
        System.out.println("服务停止,将当前节点全部的循环消息推送全部执行一次");
        Set<Timeout> timeoutSet = timeWheel.stop();
        if (CollectionUtils.isNotEmpty(timeoutSet)){
            timeoutSet.forEach(timeout -> {
                CycleWSPTimerTaskImpl task = (CycleWSPTimerTaskImpl)timeout.task();
                WSPMessage wspMessage = task.getWspMessage();
                executeCycleMessage(wspMessage.getRouteKey(),wspMessage.getMessageId());
            });
        }
    }

    /**
     * 推送一次消息
     * @param wspMessage
     */
    public void pushMessage(WSPMessage wspMessage) {
        try {
            kafkaWSPMessageProducer.sendWSPMessage(wspMessage);
        } catch (Exception e) {
            LOGGER.error("消息推送发生异常",e);
        }
    }

    /**
     * 接到MQ后处理消息
     * @param wspMessage
     */
    public void handleMessage(WSPMessage wspMessage){
        try {
            wspRoute.routeMessage(wspMessage);
        } catch (Throwable e) {
            LOGGER.error("处理收到MQ后的消息异常",e);
        }
    }

    /**
     * 推送循环消息
     * @param wspMessage
     */
    public void pushCycleMessage(WSPMessage wspMessage){
        try {
            //将消息放入循环推送消息集合中
            cycleMessageManager.addCycleMessage(wspMessage);
            //消息推送到kafka中
            kafkaWSPMessageProducer.sendWSPCycleMessage(wspMessage);
        } catch (Exception e) {
            LOGGER.error("推送循环消息发生异常",e);
        }
    }

    /**
     * 处理循环推送的消息MQ
     */
    public void handleCycleMessage(String routeKey,String messageId){
        WSPMessage wspMessage = cycleMessageManager.getCycleMessageSizeByRouteKeyAndMessageId(routeKey,messageId);
        if (wspMessage==null){
            return;
        }
        //放入到延迟队列中
        timeWheel.newTimeout(new CycleWSPTimerTaskImpl(wspMessage, this),
                wspMessage.getCyclePeriod(), wspMessage.getCycleTimeUnit());
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
        //继续发送mq
        kafkaWSPMessageProducer.sendWSPCycleMessage(wspMessage);
    }


    public WebSocketPushServer() {
    }


    public void setWspRoute(WSPRoute wspRoute) {
        this.wspRoute = wspRoute;
    }

    public void setCycleMessageManager(CycleMessageManager cycleMessageManager) {
        this.cycleMessageManager = cycleMessageManager;
    }

    public void setKafkaWSPMessageProducer(KafkaWSPMessageProducer kafkaWSPMessageProducer) {
        this.kafkaWSPMessageProducer = kafkaWSPMessageProducer;
    }
}
