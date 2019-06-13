package singleton;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zhengbing on 2019-06-09.
 * Since Version
 */
public class SingletonStaticAreaTest {

    @Test
    public void getInstance(){
        System.out.println(SingletonStaticArea.getInstance());
        System.out.println(SingletonStaticArea.getInstance());
    }

}