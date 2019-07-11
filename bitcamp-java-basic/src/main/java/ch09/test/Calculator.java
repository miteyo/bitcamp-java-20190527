package ch09.test;

// 계산 기능과 관련된 메서드를 별도의 블록으로 분리할 때 사용하는 문법이 "클래스"이다.
public class Calculator {

  // 개별적으로 관리되어야 할 값은 인스턴스 필드에 저장해야한다.
  int result; // =0 안해도 자동 초기화

  // 인스턴스 메소드를 사용할때는 반드시 그 인스턴스의 주소를 나타내야한다. calc2.plus();
  // 모든 인스턴스 메서드는 호출될 때 넘겨 받은 인스턴스 주소를 내부에 미리 생성한(built-it) 로컬변수 this에 보관한다.
  void plus(int a) {
    this.result += a; // 같은 클래스의 멤버일떄는 Calculator. 할 필요 없다.기존 result에 a를 더한다.
                      // calc1로 plus 호출하면 calc1의 주소를 갖는다. this에 calc1의 주소가 들어있다.
  }

  void minus(int a) {
    this.result -= a;
  }

  void multiple(int a) {
    this.result *= a;
  }

  void divide(int a) {
    this.result /= a;
  }


}


