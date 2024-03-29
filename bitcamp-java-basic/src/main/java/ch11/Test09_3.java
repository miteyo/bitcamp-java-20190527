// Wrapper 클래스 : 사용 후
package ch11;

import java.util.Date;

public class Test09_3 {
  public static void main(String[] args) {
    // Primitive Type의 값을 전달할 때는 Test09_2의 경우와 같이 각 타입의 값을 받는 메서드를 여러개 만들어야 하지만,
    // Wrapper 클래스의 인스턴스를 사용하면 타입의 종류와 상관없이 파라미터로 값을 받는 메서드를 한개만 만들어도 된다.
    Integer obj1 = Integer.valueOf(100);
    Float obj2 = Float.valueOf(3.14f);
    Character obj3 = Character.valueOf('H');
    Date obj4 = new Date();

    printObject(obj1);
    printObject(obj2);
    printObject(obj3);
    printObject(obj4);
  }

  
  //레퍼클래스로 객체를 만들면 메소드 1개로 가능.
  static void printObject(Object obj) { // 레퍼클래스는 오브잭트클래스의 자손이다. 자손들은 다 넘겨줄 수 있다.
    System.out.println("값 => " + obj.toString());
  }
}


