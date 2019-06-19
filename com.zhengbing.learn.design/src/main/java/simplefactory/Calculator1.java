package simplefactory;

import java.util.Scanner;

/**
 * 简单工厂模式一：初学实现两个数计算的方法
 *
 * Created by zhengbing on 2019-06-18.
 * Since Version 1.0
 */
public class Calculator1 {

  public static void main(String[] args) {

      Scanner typein = new Scanner( System.in );

      // 变量名命名不规范
      System.out.println("请输入数字A:");
      String A = typein.next();
      System.out.println("请输入运算符号（+、—、*、/）:");
      String B = typein.next();
      System.out.println("请输入数字B:");
      String C = typein.next();
      String D = "";

      // 条件判断 这种写法意味着每个条件都要做判断，等于计算机做了三次无用功
      if ( B.equals( "+" ) ){
          D = Double.parseDouble( A )+Double.parseDouble( C )+"";
      }
      if ( B.equals( "-" ) ){
          D = Double.parseDouble( A )-Double.parseDouble( C )+"";
      }
      if ( B.equals( "*" ) ){
          D = Double.parseDouble( A )*Double.parseDouble( C )+"";
      }
      if ( B.equals( "/" ) ){
          D = Double.parseDouble( A )/Double.parseDouble( C )+"";
      }
    System.out.println("输出结果是"+D);
  }
}
