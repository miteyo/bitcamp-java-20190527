// JVM 아규먼트 응용 II
package ch06;

import java.util.Set;

public class Test18 {
  public static void main(String[] args) {
    // JVM에 기본으로 설정되어 있는 프로퍼티를 모두 출력하라!
    //
    
    // JVM의 전체 프로퍼티 목록 가져오기
    java.util.Properties props = System.getProperties(); //getProperties 이름이 들어있는 집합의 객체  인스턴스주소를 리턴한다.
    
    java.util.Set keySet = props.keySet(); //이름만 꺼내라....keySet에..
    
    for (Object key : keySet) { //keySet에 String 들어있을 지라도  key값 object로 꺼낸다.
      String value = System.getProperty((String)key); //String 객체인 것에 대해 String이라고 명시, 아닐 시 오류.
      System.out.printf("%s = %s\n", key, value);
    }
  }
  
}















