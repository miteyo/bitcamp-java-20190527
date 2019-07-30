// 파일에 출력하기 - String buffer 객체에 출력하기
package ch22.i.character_stream;

import java.io.FileWriter;

public class Test01_2 {
  public static void main(String[] args) {
    // 문자를 '가'에서 부터 100자 출력한다.

    // 1) 값을 저장할 스트링 버퍼를 준비한다.
    try (FileWriter out = new FileWriter("temp/data.txt")) {

      // 2) '가'에서 시작하여 다음 문자를 100자 출력한다.
      for (int i = 0, ch = '가'; i < 100; i++, ch++) {
        out.write((char) (ch)); // 자바에서 문자? -> 정의된 숫자로 다루어진다. '가'는 ac00으로 정의되어있음
      }

      System.out.print("출력완료!");

    } catch (Exception e) {
      System.out.println("파일 출력 예외 발생!");
      e.printStackTrace();
    }
  }
}
