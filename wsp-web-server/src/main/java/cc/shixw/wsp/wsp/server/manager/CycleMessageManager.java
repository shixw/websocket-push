package cc.shixw.wsp.wsp.server.manager;

import cc.shixw.wsp.wsp.api.entity.WSPMessage;

/**
 * @Description:
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/26 19:33
 */
public interface CycleMessageManager {

    /**
     * 添加消息到唯一ID中对应的循环推送任务集合中
     * @param wspMessage
     */
    public void addCycleMessage(WSPMessage wspMessage);


    /**
     * 取消循环任务
     */
    public void removeCycleMessage(String routeKey,String messageId);

    /**
     * 根据routekey查询当前通道到对应的推送任务的数量
     * @param routeKey
     */
    public WSPMessage getCycleMessageSizeByRouteKeyAndMessageId(String routeKey,String messageId);
}
