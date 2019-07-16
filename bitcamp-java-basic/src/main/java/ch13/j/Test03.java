// 추상 클래스: 파라미터로 사용하기
package ch13.j;

public class Test03 {

  public static void main(String[] args) {
    m1(new DumpTruck()); // Ok;

    m2(new DumpTruck()); // 추상클래스를 상속받은 서브클래스의 인스턴스를 써랴~
    m2(new Convertible());


  }

  static void m1(DumpTruck car) {
    // 파라미터가 DumpTruck이면,
    // 이 메소드를 호출할 때 반드시 DumpTruck의 인스턴스나, 또는 DumpTruck의 하위클래스의 인스턴스를 넘기라는 뜻이다.
  }

  static void m2(Car car) { // 추상클래스를 파라미터로 선언하면 어떡하니?? -> car 직접 받지 못하는거 알고있음. 그러니 car의 하위클래스, 하위 객체라도
                            // 하나를 달라.
    // 파라미터가 Car이다.
    // -> 이 메서드를 호출할 때 Car의 !하위클래스!의 인스턴스를 넘기라는 뜻이다.
    // Car는 추상클래승이기 때문에 인스턴스를 생성할 수 없다.
  }

}
