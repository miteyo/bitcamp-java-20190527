// super 키워드 
package ch14.b;

public class C extends A {
  
  @Override public void m1(int a) {
    System.out.println("C의 m1()");
    // 오버라이딩 전의 메서드를 호출하고 싶다면 super 레퍼런스를 사용하라!
    System.out.println(a + 100);
    super.m1(a); //상위클래스인 A의 m1()호출
  } 
  
  public void m4() {
    System.out.println("C의 m4()");
    this.m1(100); //C클래스의의 인스턴스 주소 obj ->C에 m1() 바로위에 있다
    this.m2("okok", 100); // this는 현재 클래스에 없으니 상위클래스의 A클래스의 m2()
    super.m2("haha", 200); //C의 수퍼클래스는 A! 따라서 A!클래스
  }
  
  public static void main(String[] args) {
    C obj = new C();
    obj.m4(); //m4로 간다.
  }
  
}

//this.메서드명();
//  this에 저장된 인스턴스의 !클래스!에서 부터 메서드를 찾아올라 간다.
//
//
//super.메서드명();
//  super클래스에서부터 메서드를 찾아 올라간다.
//
//
