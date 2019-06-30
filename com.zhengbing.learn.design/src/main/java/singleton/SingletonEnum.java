package singleton;

/**
 * 枚举版实现单例模式
 *
 * Created by zhengbing on 2019-06-08.
 * Since Version 1.0
 */
public enum SingletonEnum implements SingletonEnumInterface {

    INSTANCE {
        @Override
        public void doSomeThing() {
            System.out.println( "china is good!!!" );
        }
    };

    public static SingletonEnumInterface getInstance() {
        return SingletonEnum.INSTANCE;
    }
}