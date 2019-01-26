package cc.shixw.wsp.wsp.server.timer;

import cc.shixw.wsp.wsp.api.entity.WSPMessage;
import cc.shixw.wsp.wsp.server.WebSocketPushServer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

/**
 * @Description:
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/26 18:03
 */
public class CycleWSPTimerTaskImpl implements TimerTask {

    private WSPMessage wspMessage;
    private WebSocketPushServer server;

    public CycleWSPTimerTaskImpl(WSPMessage wspMessage,WebSocketPushServer server) {
        this.wspMessage = wspMessage;
        this.server = server;
    }

    @Override
    public void run(Timeout timeout) throws Exception {
        server.executeCycleMessage(wspMessage.getRouteKey(),wspMessage.getMessageId());
    }
}
