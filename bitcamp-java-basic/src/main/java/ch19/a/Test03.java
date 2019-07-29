// 중첩클래스 사용 1 : static nested class 와 inner 클래스 사용.
package ch19.a;

import ch19.a.sub.C;

class X { //static 부터 메모리 영역에 생긴다. class B는 new 로 생성해야 생김. 
  
  static class A{
    static void m7() {
    }
    
  }
  
  class B{}
  
  
  static void m1() {
    //같은 static 멤버이기 때문에 static nested class인 A를 바로 사용할 수 있다.
    A obj = new A();
    
    //스태틱 멤버는 인스턴스 멤버를 바로 사용할 수 없다.
    B obj2; // 레퍼런스를 선언할 때는 괜찮다.
    //obj2 = new B(); // 컴파일 오류! 
    
    // 컴파일 오류가 발생한 이유?
    // => 다음과 같이 static 메소드에서 인스턴스 메소드를 호출하지 못하는 이유와 같다.
    //    static 메서드에는 인스턴스 주소를 담은 this라는 변수가 없기 때문이다.
    // => 인스턴스 멤버를 사용하려면 반드시 인스턴스 주소가 있어야 한다.
    //m2(); //컴파일 오류!
    
    //만약 인스턴스 멤버를 사용해야 한다면 다음과 같이 별도로 인스턴스를 생성한 후 사용해야 한다.
    X x = new X();
    x.m2(); //Ok!
    
    //당연히 인스턴스 주소만 있다면 X의 inner 클래스인 B객체로 생성할 수 있다.
    obj2 = x.new B(); // 실무에서는 이렇게 외부의 인스턴스를 가지고 inner 클래스를 생성하는 방식을 거의 사용하지 않는다.
  }
  
  void m2() { //인스턴스 멤버다.this가 내장되어있어서 this에 인스턴스 주소가 들어있어야만 호출 가능
    //인스턴스 멤버에서 static nested class 와 inner클래스 사용하기.
    
    //스태틱 멤버는 인스턴스 멤버에서도 자유롭게 사용할 수 있다.
    // 즉 다음과 같이 인스턴스 메서드에서 스태틱 메서드를 자유롭게 호출하듯이,
    m1(); //OK!
    
    //인스턴스 메서드에서 static nested class를 자유롭게 바로 사용할 수 있다.
    A obj = new A();// 이렇게 사용 하지 말기를..
    
    //인스턴스 메서드에서 다른 인스턴스 멤버를 자유롭게 사용하듯이
    this.a = 100; //this 생략 가능
    m3(); 
    B obj2 = this.new B();
    obj2 = new B();
  }
  
  int a;
  void m3() { }
 
  
}

public class Test03 {
  
  public static void main(String[] args) {
    //다른 클래스에서 중첩 클래스를 사용하기.
    // => static nested class 사용
    //C.m1();
    
    X.A obj = new X.A();
    
    //=> non static nested class(inner class)사용
    X.B obj2;
    
    //X의 inner 클래스를 사용 하려면 반드시 X의 인스턴스가 있어야 한다.
    //obj2 = new X.B(); //컴파일 오류!
    //obj2 = new B(); //컴파일 오류!
    
    //컴파일 오류를 가능하게 하려면? =>반드시 인스턴스 주소가 있어야한다.
    X x = new X();
    obj2 = x.new B(); 
    
  }

}

