package cc.shixw.wsp.wsp.server.service.impl;

import cc.shixw.wsp.wsp.api.entity.WSPMessage;
import cc.shixw.wsp.wsp.api.service.WebSocketPushService;
import cc.shixw.wsp.wsp.server.WebSocketPushServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description:
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/26 12:50
 */
@Component
public class WebSocketPushServiceImpl implements WebSocketPushService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketPushServiceImpl.class);
    @Resource
    private WebSocketPushServer webSocketPushServer;

    @Override
    public void push(WSPMessage wspMessage) {
        webSocketPushServer.pushMessage(wspMessage);
    }

    @Override
    public void pushCycleMessage(WSPMessage wspMessage) {
        webSocketPushServer.pushCycleMessage(wspMessage);
    }

    @Override
    public void cancelCycleMessage(String uuid, String route, String messageId) {
        webSocketPushServer.cancelCycleMessage(uuid+route,messageId);
    }
}
