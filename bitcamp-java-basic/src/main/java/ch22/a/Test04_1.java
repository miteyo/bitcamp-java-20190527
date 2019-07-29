// java.io.File 클래스 : 디렉토리 삭제
package ch22.a;

import java.io.File;

public class Test04_1 {

  public static void main(String[] args) throws Exception {
    
    File dir = new File("temp2");
    if (dir.delete()) {                             // delete() 지정된 파일 삭제
      System.out.println("디렉토리 삭제됨.");
    } else {
      System.out.println("디렉토리 삭제 못함.");
    }
  }
}





