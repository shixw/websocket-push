package cc.shixw.wsp.wsp.web.server.controller;

import cc.shixw.wsp.wsp.web.server.entity.WSPMessage;
import cc.shixw.wsp.wsp.web.server.service.WebSocketPushService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 消息发送控制器
 * @Description:
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/25 23:11
 */
@RestController
@RequestMapping("/wsp/open")
public class WebSocketOpenPushController {

    @Resource
    private WebSocketPushService wspPushService;


    @RequestMapping(path = "/push/{uuid}/{route}",method = RequestMethod.POST)
    public void push(@PathVariable String uuid,@PathVariable String route, String message){
        wspPushService.push(new WSPMessage(uuid, route, message));
    }
}
