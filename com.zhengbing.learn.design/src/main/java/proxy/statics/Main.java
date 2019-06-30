package proxy.statics;

import common.SMSService;
import common.SMSServiceImpl;

/**
 * Created by zhengbing on 2019-06-26. Email mydreambing@126.com Since Version 1.0
 */
public class Main {

    public static void main(String[] args) {
        //
        SMSService smsService = new SMSServiceImpl();
        smsService.sendMessage();
        SMSSenderProxy smsSenderProxy = new SMSSenderProxy(smsService);
        smsSenderProxy.sendMessage();
    }
}
