package cc.shixw.wsp.wsp.web.server.service.impl;

import cc.shixw.wsp.wsp.web.server.entity.WSPMessage;
import cc.shixw.wsp.wsp.web.server.route.WSPRoute;
import cc.shixw.wsp.wsp.web.server.service.WebSocketPushService;
import cc.shixw.wsp.wsp.web.server.service.WebSocketSessionService;
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
    private WSPRoute wspRoute;

    @Override
    public void push(WSPMessage wspMessage) {
        try {
            wspRoute.routeMessage(wspMessage);
        } catch (Exception e) {
            LOGGER.error("XXXXX",e);
        }
    }
}
