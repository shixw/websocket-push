package cc.shixw.wsp.wsp.server.route;

import cc.shixw.wsp.wsp.api.entity.WSPMessage;

/**
 * @Description:
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/26 19:04
 */
public interface WSPRoute {

    public void routeMessage(WSPMessage wspMessage);
}
