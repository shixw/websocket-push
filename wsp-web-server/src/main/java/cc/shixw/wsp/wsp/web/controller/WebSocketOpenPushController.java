package cc.shixw.wsp.wsp.web.controller;

import cc.shixw.wsp.wsp.api.entity.WSPMessage;
import cc.shixw.wsp.wsp.api.service.WebSocketPushService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

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
    public void push(@PathVariable String uuid,@PathVariable String route,String messageId, String message){
        wspPushService.push(new WSPMessage(uuid, route,messageId, message));
    }

    @RequestMapping(path = "/pushCycleMessage/{uuid}/{route}",method = RequestMethod.POST)
    public void pushCycleMessage(@PathVariable String uuid,@PathVariable String route,String messageId, String message,long cyclePeriod){
        wspPushService.pushCycleMessage(new WSPMessage(uuid, route,messageId, message,cyclePeriod, TimeUnit.SECONDS));
    }

    @RequestMapping(path = "/cancelCycleMessage/{uuid}/{route}",method = RequestMethod.POST)
    public void cancelCycleMessage(@PathVariable String uuid,@PathVariable String route,String messageId){
        wspPushService.cancelCycleMessage(uuid, route,messageId);
    }
}
