// 예외처리 연습2 - try~ catch~ 예외처리
package ch21.g;

public class Test02_1 {

  public static void main(String[] args) {

    try {
      System.out.println(100 / 0);
    } catch (RuntimeException e) {
      System.out.println("나누기 오류입니다.");
    }
    // 이런 RuntimeException 예외를 개발자가 조심해야 한다.
    // 컴파일러가 알려주지 않기 때문에 개발자가 스스로 판단하여 
    // 예외를 try~ catch~ 로 직접 처리할 것인지 아니면
    // 호출자에게 전달 할 것인지 결정해야 한다.

  }

}
