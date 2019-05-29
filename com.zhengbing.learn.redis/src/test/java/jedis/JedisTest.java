package jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by zhengbing on 2019/2/21.
 * Since Version 1.0
 */
public class JedisTest {


    /**
     * test connect jedis server and  set a String parameter  with single Object
     */
    @Test
    public void testJedis(){
        Jedis jedis = new Jedis("localhost");

        jedis.set( "name","good" );
        String name  = jedis.get( "name" );
        System.out.println(name);
        jedis.close();

    }
}
