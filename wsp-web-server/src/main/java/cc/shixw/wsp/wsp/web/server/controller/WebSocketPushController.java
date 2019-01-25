package cc.shixw.wsp.wsp.web.server.controller;

import cc.shixw.wsp.wsp.web.server.ws.WebSocketServer;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/wsp")
public class WebSocketPushController {

    @RequestMapping(path = "/push/{uuid}/{route}",method = RequestMethod.POST)
    public void push(@PathVariable String uuid,@PathVariable String route, String message){
        WebSocketServer.sendInfoByRoute(message,uuid,route);
    }
}
