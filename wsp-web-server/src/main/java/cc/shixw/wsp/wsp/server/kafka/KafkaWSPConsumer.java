package cc.shixw.wsp.wsp.server.kafka;

import cc.shixw.wsp.wsp.api.entity.WSPMessage;
import cc.shixw.wsp.wsp.server.WebSocketPushServer;
import com.alibaba.fastjson.JSON;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description:
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/27 16:31
 */
@Component
public class KafkaWSPConsumer {

    @Resource
    private WebSocketPushServer webSocketPushServer;

    @KafkaListener(topics = "wsp-message")
    public void handlerWSPMessage(String message){
        webSocketPushServer.handleMessage(JSON.parseObject(message, WSPMessage.class));
    }

    @KafkaListener(topics = "wsp-cycle-message")
    public void handlerWSPCycleMessage(String message){
        WSPMessage wspMessage = JSON.parseObject(message, WSPMessage.class);
        webSocketPushServer.handleCycleMessage(wspMessage.getRouteKey(),wspMessage.getMessageId());
    }
}
