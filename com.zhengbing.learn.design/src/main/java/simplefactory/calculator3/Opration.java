package simplefactory.calculator3;

/**
 * Created by zhengbing on 2019-06-19.
 * Since Version 1.0
 */
public class Opration {

    public static double getResult( double strNumberA, double strNumberB, String oprate ){

        double strResult = 0d;

        switch ( oprate ){
            case "+":
                strResult =  strNumberA + strNumberB;
                break;
            case "-":
                strResult =  strNumberA - strNumberB;
                break;
            case "*":
                strResult =  strNumberA * strNumberB;
                break;
            case "/":
                    strResult = strNumberA /  strNumberB;
                break;

            default:

        }
        return strResult;
    }
}
