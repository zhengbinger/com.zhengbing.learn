package proxy.statics;

import common.SMSService;

/**
 * Created by zhengbing on 2019-06-26.
 * Email mydreambing@126.com
 * Since Version 1.0
 */
public class SMSSenderProxy implements SMSService {

    /**
     * 被代理的目标
     */
    private SMSService smsService;

    public SMSSenderProxy(final SMSService smsService) {
        this.smsService = smsService;
    }

    /**
     * 代理后的业务处理
     */
    @Override
    public void sendMessage() {
        System.out.println("发送短信前...");
        smsService.sendMessage();
        System.out.println("发送短信后...");
    }
}
