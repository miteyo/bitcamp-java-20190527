// 문자 배열 읽기
package ch22.b.character_stream;

import java.io.FileReader;

public class Test02_2 {

  public static void main(String[] args)  throws Exception {

    // FileReader
    // => 문자 단위로 데이터를 읽는 일을 한다.
    //
    FileReader in = new FileReader("data.txt"); //캐릭터 배열 단위로 읽는다.

    char[] cbuf = new char[1024]; //캐릭터가 2바이트니까 2048바이트를 배열 준비하고 최대 2048를 읽을 수 있다.

    int len = in.read(cbuf);

    System.out.println(len);
    for (int i = 0; i < len; i++) {
      System.out.print(cbuf[i] + ","); //읽은 데이터 출력
    }
    System.out.println();
    in.close();
    System.out.println("읽기 완료!");
  }

}
