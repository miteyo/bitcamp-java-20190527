// Object 클래스 - getClass() 와 배열의 항목 이름
package ch15;

public class Test11_3 {
  public static void main(String[] args) {
    // 배열의 클래스 정보
    String[] obj2 = new String[10];
    Class<?> classInfo = obj2.getClass();
    System.out.println(classInfo.getName()); //[Ljava.lang.String;
    
    // 배열 항목의 타입 정보를 가져온다.
    Class<?> compTypeInfo = classInfo.getComponentType(); //배열이 어떤 타입의 배열인지 리턴
    System.out.println(compTypeInfo.getName()); //java.lang.String
    
    // 값을 한 번 밖에 사용하지 않을 것이라면
    // 위의 경우처럼 한 번씩 호출하고, 리턴 값을 가지고 또 호출하는 방식으로 값을 꺼내지 않는다.
    // 체인(chain) 방식으로 호출한다.
    System.out.println(obj2.getClass().getComponentType().getName()); //java.lang.String
    //     obj 2000번지의 클래스 정보를 가져오라.배열 항목의 클래스정보가져와라.배열항목의 클래스 이름    
    //메소드1().메소드2().메소드3()
    //1의 리턴한 값.메소드2
    
    
  }
}







