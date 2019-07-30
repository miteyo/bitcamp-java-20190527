// 바이트 배열에 출력할 때 Streaming API를 사용하기
package ch22.i.byte_stream;

import java.io.FileOutputStream;

public class Test01_2 {
  public static void main(String[] args) {
    // 1~100중에서 짝수만 파일에 출력해보자!

    // 1) 값을 출력할 파일출력 객체 준비
    try(FileOutputStream out = new FileOutputStream("temp/data.bin")){

      // 2) 짝수를 파일에 출력한다.
      for (int i = 1; i <= 100; i++) {
        if (i % 2 == 0)
          out.write(i);
      }
      
      System.out.print("출력완료!");
      
    } catch(Exception e) {
       System.out.println("파일 출력 예외 발생!");
       e.printStackTrace();
    }
  }
}
