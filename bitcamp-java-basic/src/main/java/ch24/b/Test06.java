// JVM의 전체 스레드 계층도
package ch24.b;

public class Test06 {

  public static void main(String[] args) {
    
    // JVM의 최상위 스레드 그룹인 system의 계층도 출력하기
    //
    Thread mainThread = Thread.currentThread();
    ThreadGroup mainGroup = mainThread.getThreadGroup();
    ThreadGroup systemGroup = mainGroup.getParent();
    
    printThreads(systemGroup, "");
  }
  
  static void printThreads(ThreadGroup tg, String indent) {
    System.out.println(indent + tg.getName() + "(TG)");
    
    // 현재 스레드 그룹에 소속된 스레드들 출력하기
    Thread[] threads = new Thread[10];
    int size = tg.enumerate(threads, false);
    for (int i = 0; i < size; i++) {
      System.out.println(indent + "  ==> " + threads[i].getName() + "(T)");
    }
    
    // 현재 스레드 그룹에 소속된 하위 스레드 그룹들 출력하기
    ThreadGroup[] groups = new ThreadGroup[10];
    size = tg.enumerate(groups, false);
    for (int i = 0; i < size; i++) {
      printThreads(groups[i], indent + "  ");
    }
  }

}

// JVM의 스레드 계층도:
// system(TG)
//   ==> Reference Handler(T) //인스턴스의 주소를 몇개의 변수가 갖고있는지
//   ==> Finalizer(T)         // 가비지 컬렉터 처럼 내부적 메모리가 부족하면 청소
//   ==> Signal Dispatcher(T) 
//   main(TG)               
//     ==> main(T) : main() 메서드를 호출한다.
//   InnocuousThreadGroup(TG) //
//     ==> Common-Cleaner(T)










