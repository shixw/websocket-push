package cc.shixw.wsp.wsp.web;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/26 21:50
 */
@Component
public class WSPWebDisposableBean implements DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("=====================================");
    }
}
