package cc.shixw.wsp.wsp.web.server.controller;

import cc.shixw.wsp.wsp.web.server.entity.WSPMessage;
import cc.shixw.wsp.wsp.web.server.websocket.WebSocketServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息发送控制器
 * @Description:
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/25 23:11
 */
@RestController
@RequestMapping("/wsp/internal")
public class WebSocketInternalPushController {

    @RequestMapping(path = "/push",method = RequestMethod.POST)
    public void push(WSPMessage wspMessage){
        WebSocketServer.sendInfoByRoute(wspMessage);
    }
}
