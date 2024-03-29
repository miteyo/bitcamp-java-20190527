// String buffer 객체에 출력하기 - 직접 스트링 버퍼를 다루는 경우
package ch22.i.character_stream;

public class Test01_1 {
  public static void main(String[] args) throws Exception {
    //문자를 '가'에서 부터 100자 출력한다.

    // 1) 값을 저장할 스트링 버퍼를 준비한다.
    StringBuffer sb = new StringBuffer();

    // 2) '가'에서 시작하여 다음 문자를 100자 출력한다. 
    for (int i = 0, ch = '가'; i<100; i++, ch++) {
      sb.append((char)(ch)); //자바에서 문자? -> 정의된 숫자로 다루어진다. '가'는 ac00으로 정의되어있음
    }

    // 3) 스트링 버퍼에 저장된  값을 확인해보자!
    System.out.println(sb.toString());
    System.out.println();
  }
}
