package cc.shixw.wsp.wsp.server.kafka;

import cc.shixw.wsp.wsp.api.entity.WSPMessage;
import com.alibaba.fastjson.JSON;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @Description:
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/27 16:25
 */
public class KafkaWSPMessageProducer {

    private final KafkaTemplate kafkaTemplate;

    public KafkaWSPMessageProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendWSPMessage(WSPMessage wspMessage){
        kafkaTemplate.send("wsp-message", JSON.toJSONString(wspMessage));
    }

    public void sendWSPCycleMessage(WSPMessage wspMessage){
        kafkaTemplate.send("wsp-cycle-message", JSON.toJSONString(wspMessage));
    }
}
