package ch08;

import java.util.Scanner;

class My11 {
  static int a;
  static {
    System.out.println("오호라!");
  }

  static {
    System.out.println("이것이 스태틱 블록이네! 여러 개를 선언할 수 있네!");
  }

  static void m1() {
    System.out.println("나를 호출하셨군요!!!");
  }
}


public class Practice {
  public static void main(String[] args) {


    Scanner keyScan = new Scanner(System.in);

    System.out.println("***************");

    My11 obj1;
    keyScan.nextLine();


    System.out.println("------------------");
    keyScan.nextLine();

    My11.m1();
    keyScan.nextLine();

    My11.m1(); 
  }

}
