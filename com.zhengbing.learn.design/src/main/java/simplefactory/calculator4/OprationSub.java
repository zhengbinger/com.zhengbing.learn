package simplefactory.calculator4;

/**
 * Created by zhengbing on 2019-06-19.
 * Since Version 1.0
 */
public class OprationSub extends Opration {

    public double getResult(){
        double result = 0d;
        result = getStrNumberA() - getStrNumberB();
        return result;
    }
}