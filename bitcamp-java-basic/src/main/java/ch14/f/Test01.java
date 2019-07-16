// 다형적 변수의 규칙 I
package ch14.f;

public class Test01 {

  public static void main(String[] args) {
    // 레퍼런스 변수 선언
    // => 어떤 타입의 레퍼런스가 있다면, 그 타입의 인스턴스 주소를 담을 수 있다는 것이다.
    // => 또한 그 타입의 서브 클래스의 인스턴스 주소도 담을 수 있다.
    A obj;
 
    obj = new A(); // OK 
    obj.m1(); // => A.m1()
    
    // 서브 클래스(타입)의 인스턴스 주소 저장 가능
    obj = new B(); // OK  
    obj.m2(); // 컴파일 오류! //   주소는 B
              // 컴파일러는 레퍼런스의 타입으로 사용할 수 있는 멤버인지 확인한다.
              // 실제 그 레퍼런스에 저장된 인스턴스가 어떤 타입인지는 따지지 않는다.
    
    obj.m1(); // B.m1()
              // JVM은 메서드를 호출할 때 실제 레퍼런스에 저장된 인스턴스의 타입에서 
              // 먼저 메서드를 찾는다.
              // 따라서 오버라이딩 메서드가 있다면 그 메서드가 호출된다.
    
    int a;
    //a = 2.5;

  }

}
