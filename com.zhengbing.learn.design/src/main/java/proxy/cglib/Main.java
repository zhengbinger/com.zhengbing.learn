package proxy.cglib;

import proxy.SMSServiceImpl;

/**
 * Created by zhengbing on 2019-06-26.
 * Email mydreambing@126.com
 * Since Version 1.0
 */
public class Main {

    public static void main( String[] args ) {

        SMSServiceImpl smsService = new SMSServiceImpl();
        CglibProxy cglibProxy = new CglibProxy();
        SMSServiceImpl smsServiceProxy = (SMSServiceImpl) cglibProxy.getInstance( smsService );
        smsService.sendMessage();
    }
}
