// "main" 스레드
package ch24.b;

public class Test01 {

  public static void main(String[] args) {
    // JVM은 main() 메서드를 호출할 때 직접 실행하지 않고 
    // 스레드를 생성하여 main()을 호출한다.
    // 이렇게 main()을 호출하는 스레드를 "main" 스레드라 부른다.

    // 현재 코드를 실행하는 스레드를 알아내기
    Thread currThread = Thread.currentThread(); //현재 명령문을 실행했을때의 스레드가 누구인지 정보를 리턴
    System.out.println(currThread.getName());
    
  }

}

// JVM의 스레드 계층도:
// main(T) : 주 스레드
