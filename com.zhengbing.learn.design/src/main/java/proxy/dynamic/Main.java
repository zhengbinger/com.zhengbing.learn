package proxy.dynamic;

import proxy.SMSService;
import proxy.SMSServiceImpl;

import java.lang.reflect.Proxy;

/**
 * 基于 jdk 实现动态代理
 *
 * <p>不足之处：
 *
 * <p>JDK动态代理针对直接注入类类型的，就代理不了。
 *
 * <p>Created by zhengbing on 2019-06-20. Since Version 1.0
 */
public class Main {

    public static void main( String[] args ) {
        // 1.0  基础版本实现
        SMSService smsService = new SMSServiceImpl();

        // 2.0 JDK动态代理实现

        // 注意Proxy.newProxyInstance()方法接受三个参数：
        //
        //    ClassLoader loader:指定当前目标对象使用的类加载器,获取加载器的方法是固定的
        //    Class<?>[] interfaces:指定目标对象实现的接口的类型,使用泛型方式确认类型  只能是interface
        //    InvocationHandler:指定动态处理器，执行目标对象的方法时,会触发事件处理器的方法s
        smsService = (SMSService) Proxy.newProxyInstance( Main.class.getClassLoader(), new Class[]{ SMSService.class }, new MoneyCountInvocationHandler( smsService ) );
        // 结果输出
        smsService.sendMessage();
        smsService.sendMessage();
    }
}
