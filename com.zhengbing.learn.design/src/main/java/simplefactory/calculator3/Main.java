package simplefactory.calculator3;

import java.util.Scanner;

/**
 * Created by zhengbing on 2019-06-19.
 * Since Version 1.0
 */
public class Main {

  public static void main(String[] args) {
    //
      try{

          Scanner typein = new Scanner( System.in );

          System.out.println("请输入数字A:");
          String strNumberA = typein.next();
          System.out.println("请输入运算符号（+、—、*、/）:");
          String strOprate = typein.next();
          System.out.println("请输入数字B:");
          String strNumberB = typein.next();
          double strResult = Opration.getResult( Double.parseDouble( strNumberA ),Double.parseDouble( strNumberB ),strOprate );

          System.out.println("输出结果是："+strResult);

      }catch ( Exception e ){
          System.out.println("您的输入有误！");
      }

  }
}
