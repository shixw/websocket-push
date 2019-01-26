package cc.shixw.wsp.wsp.web.server.service;

import cc.shixw.wsp.wsp.web.server.entity.WSPMessage;

/**
 * @Description: 消息推送接口
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/26 12:14
 */
public interface WebSocketPushService {

    /**
     * 只推送一次,如果推送失败或者当前路由没有连接则将消息丢弃
     */

    void push(WSPMessage wspMessage);
}
