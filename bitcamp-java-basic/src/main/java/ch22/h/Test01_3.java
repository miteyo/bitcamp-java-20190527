// 파일 입출력과 예외 처리 - try with resource
package ch22.h;

import java.io.FileOutputStream;
import java.io.IOException;

public class Test01_3 {
  public static void main(String[] args) throws Exception {


    // java.lang.AutoCloseable 구현체인 경우
    // try 블록을 벗어나기 전에 자동으로 close()가 호출되게 하는 문법.
    // => 이 문법을 사용하면 개발자가 finally블록에서 일일이 close()를 호출하는 번거로움을 피할 수 있다.
    // 문법?
    // => try(AutoCloseable 객체 준비) {..} catch(..) {..} 

    try (FileOutputStream out = new FileOutputStream("temp3/data.bin")) {
      out.write(0x55);

    } catch (IOException e) {
      System.out.println("파일 입출력 예외발생!");
      e.printStackTrace();
    }
    System.out.println("출력 완료!");

  }
}
