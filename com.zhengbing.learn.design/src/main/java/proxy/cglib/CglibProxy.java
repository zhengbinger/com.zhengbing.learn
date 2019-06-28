package proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by zhengbing on 2019-06-26.
 * Email mydreambing@126.com
 * Since Version 1.0
 */
public class CglibProxy implements MethodInterceptor {

    private Object target;

    public Object getInstance( final Object target ) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass( this.target.getClass() );
        enhancer.setCallback( this );
        return enhancer.create();
    }

    @Override
    public Object intercept( Object object, Method method, Object[] args, MethodProxy methodProxy ) throws Throwable {
        System.out.println( "发送短信前..." );
        Object result = methodProxy.invoke( object, args );
        System.out.println( "发送短信后..." );
        return result;
    }
}
