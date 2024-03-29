// 애플리케이션 예외의 종류: Exception 계열의 예외 처리
package ch21.c;

public class Test02_1 {

  public static void main(String[] args) {

    // Exception 계열의 예외 처리 방법
    // 1) try ~ catch로 예외 받기
    //    try {
    //      예외 발생 코드
    //    } catch (예외 파라미터) {
    //          예외처리 코드
    //    }
    
    // 2) 호출자에게 예외 처리를 떠넘기기 -- Test03_1
    // void 메서드() throws 예외클래스명, 예외클래스명, ... {
    // 예외가 발생할 수 있는 코드
    // }
    //

    // '방법1' 적용
    // => try ~ catch 로 예외 처리하기
    try {
      int result = divide(100, 0);
      System.out.println(result); // 정상적일 때만 result를 출력

    } catch (Exception e) {
      System.out.println("계산오류!");
    }

    System.out.println("종료!");

  }


  static int divide(int a, int b) throws Exception { // b==0이면 Exception 발생 할 수 있다.
    if (b == 0)
      throw new Exception("0으로 나눌 수  없습니다.");
    return a / b;
  }



}
