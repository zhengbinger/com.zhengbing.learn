package simplefactory.calculator4;

/**
 * Created by zhengbing on 2019-06-19.
 * Since Version 1.0
 */
public class OprationFactory {

    public static Opration getOpration(String oprate){
        Opration opration = null;
        switch ( oprate ){
            case "+":
                opration = new OprationAdd();
                break;

            case "-":
                opration = new OprationSub();
                break;

            case "*":
                opration = new OprationMul();
                break;

            case "/":
                opration = new OprationDiv();
                break;
        }
        return  opration;
    }

}
