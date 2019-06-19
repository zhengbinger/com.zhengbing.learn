package simplefactory.calculator4;

/**
 * Created by zhengbing on 2019-06-19.
 * Since Version 1.0
 */
public class Main {

  public static void main(String[] args) throws Exception {
    //
      Opration opration = OprationFactory.getOpration( "*" );
      opration.setStrNumberA( 1 );
      opration.setStrNumberB( 3 );
      System.out.println(opration.getResult());
  }
}
