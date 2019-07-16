
// Overriding과 this/super

package ch14.b;

public class Test {

  public static void main(String[] args) {
    X2 obj = new X2();
    obj.test();
    System.out.println("-----------------------------");

    X3 obj2 = new X3(); //X3이 없으면, 그의 상위 클래스에서 찾아본다.
    obj2.test();
    System.out.println("-----------------------------");

    X4 obj3 = new X4();  // test() 메소드의 this는 X4의 레퍼런스이다.
    obj3.test();
    System.out.println("-----------------------------");

    X5 obj4 = new X5();
    obj4.test();

  }

  
  //super. 은 메소드가 있는 X2니까 이것의 상위 클래스인 X1
}
