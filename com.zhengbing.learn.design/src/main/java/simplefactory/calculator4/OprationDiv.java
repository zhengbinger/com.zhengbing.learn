package simplefactory.calculator4;

/**
 * Created by zhengbing on 2019-06-19.
 * Since Version 1.0
 */
public class OprationDiv extends Opration {

    @Override
    public double getResult() throws Exception {
        double result = 0d;
        if ( getStrNumberB() == 0 ){
            throw  new Exception( "除数不能为0" );
        }
        result = getStrNumberA()/getStrNumberB();

        return result;

    }
}
