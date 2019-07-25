// 예외 처리하기 - try ~ catch ~ finally 
package ch21.b;

import java.util.Scanner;

public class Test01 {
  public static void main(String[] args) {
    
    Scanner keyboard = new Scanner(System.in);
    System.out.print("숫자를 입력하세요: ");
    String input = keyboard.nextLine();
    
    try { //return 전에 finally 실행
      // 예외가 발생할 수 있는 코드를 두는 블록 
      int value = Integer.parseInt(input);
      System.out.println(value % 2 == 0 ? "짝수입니다." : "홀수입니다.");
      return; // main() 메서드를 나가기 전에 finally 블록이 실행된다.
      
    } catch (Exception e) { //return 전에 finally
      // 예외가 발생했을 때 실행되는 블록
      System.out.println("입력 값이 유효하지 않습니다.\n정수 값을 입력하세요!");
      return; // main() 메서드를 나가기 전에 finally 블록이 실행된다.
      
    } finally { //try return 전에 실행된다. 개발자가 강제적으로 자원을 해제시키기 아주 좋은 블록이다.
      // 정상적으로 실행하든 예외가 발생되든 무조건 try ~ catch 블록을 벗어나기 전에 실행되는 블록
      // => 필수 블록이 아니다. 생략해도 된다.
      System.out.println("종료합니다!");
      keyboard.close();
    }
    
    //finally 왜?
    // Scanner 왜 닫아야 하나?
    // => 자바는 잠깐 Test하는 프로그램이 아니다. 자바는 서버프로그램에 주로 사용되고 이는 365내내 실행 될 것이다.
    //    Scanner처럼 입출력과 메모리와 관련된 한정된 자원을 사용 할 때는, 사용한 후 자원을 해제시켜주는 것이 좋다.
    //    생성하고 쓰다가  garbage가 되기 전에 해제 시키기 위해 close()호출해주자.
    //    Scanner
    
  }
}
