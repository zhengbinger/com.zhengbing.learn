package simplefactory;

import java.util.Scanner;

/**
 * 简单工厂模式：优化初始例子，解决变量名不规则，条件判断浪费资源的问题
 *
 * Created by zhengbing on 2019-06-19.
 * Since Version 1.0
 */
public class Calculator2 {

  public static void main(String[] args) {

      try{

          Scanner typein = new Scanner( System.in );

          System.out.println("请输入数字A:");
          String strNumberA = typein.next();
          System.out.println("请输入运算符号（+、—、*、/）:");
          String strOprate = typein.next();
          System.out.println("请输入数字B:");
          String strNumberC = typein.next();
          String strResult = "";

          switch ( strOprate ){
              case "+":
                  strResult = Double.parseDouble( strNumberA )+Double.parseDouble( strNumberC )+"";break;

              case "-":
                  strResult = Double.parseDouble( strNumberA )-Double.parseDouble( strNumberC )+"";break;

              case "*":
                  strResult = Double.parseDouble( strNumberA )*Double.parseDouble( strNumberC )+"";break;

              case "/":
                if ( !strNumberC.equals( "0" ) ) {
                  strResult = Double.parseDouble(strNumberA) / Double.parseDouble(strNumberC) + "";
                }else {
                    strResult = "除数不能为0";
                }break;

                default:

          }
          System.out.println("输出结果是："+strResult);
      }catch ( Exception e ){
        System.out.println("您的输入有误！");
      }
  }
}
