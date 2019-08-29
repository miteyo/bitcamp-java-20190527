// 클래스(또는 인터페이스) 이름 알아내기 
// reflection API (거울처럼 클래스 내부 내용을 보여주는, 들여다 볼 수 있는  도구)
package ch27.b;

public class Test01 {

  public static void main(String[] args) throws Exception {
    
    Class<?> clazz = Calculator.class; //class? static 변수명 (클래스 정보를 담고있는)
    
    System.out.println(clazz.getName()); // 패키지 이름을 포함한 클래스명
    System.out.println(clazz.getCanonicalName()); // 패키지 이름을 포함한 클래스명
    System.out.println(clazz.getSimpleName()); // 패키지명을 제외한 클래스명
  }

}
