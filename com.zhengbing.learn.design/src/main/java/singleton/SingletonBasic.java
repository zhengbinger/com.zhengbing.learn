package singleton;

/**
 * 基础版本单例模式结构，采用静态内部类的方式实现
 *
 * Created by zhengbing on 2019-06-08.
 * Since Version 1.0
 */
public class SingletonBasic {

    public static class SingletonHolder{
        private static final SingletonBasic INSTANCE = new SingletonBasic();
    }

    public static final SingletonBasic getInstance(){
        return SingletonHolder.INSTANCE;
    }

}

