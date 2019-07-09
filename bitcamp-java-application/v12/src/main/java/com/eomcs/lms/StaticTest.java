package com.eomcs.lms;

public class StaticTest {

  public static void main(String[] args) {
    // static field 사용하기
    // => class 이름으로 사용하면 된다.

    System.out.println(Car.count);

    // instatnce field 사용하기
    Car c1;
    c1 = new Car();
    c1.no = 1;
    c1.model = "티코";
    c1.count++; //class 이름 대신, 레퍼런스를 통해 static field를 사용할수 있다. 단, 비추~!
                //개발자가 instance field라고 착각할 수 있다.
                //static field는 보통 class 이름으로 사용한다.
               // Car.count++;
    

    Car c2 = new Car();
    c2.no =2;
    c2.model = "소나타";
    c2.count++; //class 이름 대신, 레퍼런스로 사용할수 있다. 단, 비추~!
    
    System.out.printf("%d, %s\n", c1.no, c1.model);
    System.out.printf("%d, %s\n", c2.no, c2.model);
    System.out.println(Car.count);
    
  }

}
