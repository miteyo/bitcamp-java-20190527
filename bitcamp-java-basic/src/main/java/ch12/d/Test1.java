// 캡슐화(encapsulation) : 적용 전
package ch12.d;

public class Test1 {
  public static void main(String[] args) {
    Patient p = new Patient();

    p.name = "김영희";
    p.age = 20;
    p.weight = 60;
    p.height = 157;
    p.gender = Patient.WOMAN;
    System.out.println(p); // p에 스트링이 아닌것을 주면, 내부적으로 toString을 호출한다.


    // 환자를 컴퓨터에서 다루기 위해 데이터화 해야한다.
    // Patient는 이럴 목적으로 정의한 클래스이다.
    // 이렇게 Patient의 경우처럼 컴퓨터에서 다루기 위해 데이터화하여 정의하는 것을 추상화라고 부른다.
    // 꼭 데이터만 해당하는 것은 아니다.
    // MemberHandler 클래스의 경우처럼 특정 업무를 정의하는 것도 "추상화"라고 부른다.
    // 즉, 실세계의 객체(예: 사람, 물건, 업무 등)를 컴퓨터에서 다룰 수 있도록 클래스로 정의하는 행위를 "추상화"라 부른다.

    Patient p2 = new Patient();

    p2.name = "이철희";
    p2.age = 300;
    p2.weight = -50;
    p2.height = 400;
    p2.gender = Patient.MAN;
    System.out.println(p2);

    // 나이가 300이면 환자가 아니라 몬스터이다.
    // 몸무게가 -50이면 이해불가하다
    // 키가 4미터이면 이건 나무다.
    // 즉 Patient 클래스는 환자의 데이터를 저장할 목적으로 정의한 클래스인데, p2데이터를 보면 환자와 무관한 데이터를 저장하고 있다.
    // 차라리 클래스 이름을 Monster로 변경하는 것이 바람직하다.
    // 이렇게 클래스 목적에 맞지 않는 데이터가 들어갈 수 있다면, "추상화"가 무너지게 된다.
    // =>이를 방지하기 위해서는 클래스 목적(추상화 목적)에 맞추어 인스턴스 멤버(메소드, 변수)에 무효한 값이 들어가지 않도록 해야한다.
    // 그럴 목적으로 만든 문법이 캡슐화이다.

    // "캡슐화"? 추상화가 무너지지 않도록 인스턴스의 접근을 제어하는 문법이다.
    // "추상화"? 실세계의 객체를 프로그램에서 다룰 수 있도록 클래스로 정의하는것.
    // 추상화 기법? (클래스 활용 방법 2가지)
    // -데이터타입을 정의
    // -유관 메서드를 묶기



  }

}
