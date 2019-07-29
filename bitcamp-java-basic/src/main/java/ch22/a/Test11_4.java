// java.io.File 클래스 : FileFilter 사용하기 IV
package ch22.a;

import java.io.File;

public class Test11_4 {

  public static void main(String[] args) throws Exception {
    
    File file = new File(".");
    
    // anonymous class //중괄호 안에 한개: 삭제-return 삭제 - ; 삭제
    // 파라미터 한개: 괄호 삭제
    File[] files = file.listFiles (pathname -> 
         pathname.isFile() && pathname.getName().endsWith(".txt")
    );
    
    for (File f : files) {
      System.out.printf("%s %12d %s\n", 
          f.isDirectory() ? "d" : "-",
          f.length(),
          f.getName());
    }
    
  }    
}





