// 스트링 버퍼에 출력할 때 - Streaming API를 사용하기
package ch22.i.character_stream;

import java.io.StringWriter;
// ***~중요~*** 파일이 아닌, 메모리에 출력하고 싶을 때 사용한다.
public class Test01_3 {
  public static void main(String[] args) {
    // 문자를 '가'에서 부터 100자 출력한다.

    // Test01_1.java와 Test1_2.java에서 문자를 출력하는 방법이 다르다.
    // 스트링 버퍼에 출력할 때는 스트링 버퍼를 직접 다룬다
    // 그러나 파일에 출력할 때는 Stream API를 사용한다.
    // 그래서 데이터를 출력할 때 일관성이 없다. 즉 유지보수가 불편하다.
    // 이를 해결하기 위해 나온 방법이
    // 메모리(String Buffer)에 출력할 때도 Streaming API를 사용하는 것이다.

    // 1) 문자를 메모리에 출력할 수 있도록 바이트 배열 출력 스트림을 준비한다.
    try (StringWriter out = new StringWriter()) {

      // 2) 문자를 StringBuffer에 출력한다..
      // => 오호라! 파일에 출력하는 것과 똑같다.
      // => 단 FileWrite과 달리 write()를 호출하면
      // 그 값은 StringWriter 객체의 내부에 보관된다.
      // => '가'에서 시작하여 다음 문자를 100자 출력한다.
      for (int i = 0, ch = '가'; i < 100; i++, ch++) {
        out.write((char) (ch)); // 자바에서 문자? -> 정의된 숫자로 다루어진다. '가'는 ac00으로 정의되어있음
      }

      System.out.println("출력완료!");

      // ByteArrayOutputStream에 저장된 값을 꺼내보자!
      System.out.println(out.toString());

    } catch (Exception e) {
      System.out.println("파일 출력 예외 발생!");
      e.printStackTrace();
    }

  }
}
