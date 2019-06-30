package proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK动态代理模式
 * 在动态代理中我们不再需要再手动的创建代理类，我们只需要编写一个动态处理器就可以了。真正的代理对象由JDK再运行时为我们动态的来创建。
 *
 * <p>
 * 优点：相对于静态代理，动态代理大大减少了我们的开发任务，同时减少了对业务接口的依赖，降低了耦合度
 * 缺点：那就是它始终无法摆脱仅支持interface代理的桎梏，因为它的设计注定了这个遗憾。回想一下那些动态生成的代理类的继承关系图，
 * 它们已经注定有一个共同的父类叫Proxy。Java的继承机制注定了这些动态代理类们无法实现对class的动态代理，原因是多继承在Java中本质上就行不通。
 * Created by zhengbing on 2019-06-20.
 * Since Version 2.0
 */
public class MoneyCountInvocationHandler implements InvocationHandler {

    /**
     * 被代理的目标
     */
    private final Object object;

    private Double moneyCount;

    public MoneyCountInvocationHandler( Object object ) {
        this.object = object;
        this.moneyCount = 0.0;
    }

    /**
     * 包装业务处理
     *
     * @param proxy
     * @param method
     * @param args
     *
     * @return
     *
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke( object, args );
        moneyCount += 0.05;
        System.out.println( "发送短信成功，共花了：" + moneyCount + "元" );
        return result;
    }
}
