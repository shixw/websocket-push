package cc.shixw.wsp.wsp.web.controller;

import cc.shixw.wsp.wsp.api.entity.WSPMessage;
import cc.shixw.wsp.wsp.web.websocket.WebSocketServer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 内部调用消息发送控制器 通过IP调用
 * @Description:
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/25 23:11
 */
@RestController
@RequestMapping("/wsp/internal")
public class WebSocketInternalPushController {

    @RequestMapping(path = "/push",method = RequestMethod.POST)
    public void push(@RequestBody WSPMessage wspMessage) throws Exception {
        WebSocketServer.sendInfoByRoute(wspMessage);
    }
}
