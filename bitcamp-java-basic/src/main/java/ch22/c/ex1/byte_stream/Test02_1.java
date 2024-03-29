// 데이터 출력에서 버퍼 사용 - 사용 전
package ch22.c.ex1.byte_stream;

import java.io.FileOutputStream;

public class Test02_1 {
  public static void main(String[] args) throws Exception {

    FileOutputStream out = new FileOutputStream("temp/data.bin");

    System.out.println("데이터 쓰는 중...");

    long start = System.currentTimeMillis();

    for (int i = 0; i < 1000000; i++) { // 하드디스크에서 1메가를 쓸 수 있는 트랙 빈 sector에 1바이트를 쓴다.
      out.write(0x55); //55라는 1바이트를 출력하자.
    }

    long end = System.currentTimeMillis();

    System.out.println(end - start);
    
    out.close();
    System.out.println("출력 완료!");
  }
}








