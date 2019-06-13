package singleton;

/**
 * Created by zhengbing on 2019-06-09.
 * Since Version 1.0
 */
public class SingletonStaticArea {

    private static SingletonStaticArea singletonStaticArea;

    static {
        singletonStaticArea = new SingletonStaticArea();
    }

    public  static  SingletonStaticArea getInstance(){
        return singletonStaticArea;
    }

}
