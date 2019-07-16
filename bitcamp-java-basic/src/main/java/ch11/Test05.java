// String 클래스 사용법 - immutable(값을 바꿀 수 없는) 객체이다.
package ch11;

public class Test05 {
  public static void main(String[] args) {
    
    String s1 = new String("Hello");
    
    // String 인스턴스가 한 번 생성되면 절대로 값을 바꿀 수 없다.
    // => 이런 것을 "immutable 객체"라 부른다.
    String s2 = s1.replace('l', 'x');         //기존의 값을 절대 안바꾼다. 바꾼것을 새로운 인스턴스를 만들고 레퍼런스에 넘긴다.
    
    String s3 = s1.replace("ll", "dd");

    System.out.println(s1);
    System.out.println(s2);
    System.out.println(s3);
    System.out.println(s1 == s2);   //원본값은 안바뀐다. 
    System.out.println(s2 == s3);
    System.out.println(s1 == s3);
  }
}
















