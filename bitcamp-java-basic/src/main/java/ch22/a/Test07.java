// java.io.File 클래스 : 폴더 생성 후 파일 생성
package ch22.a;

import java.io.File;

public class Test07 {

  public static void main(String[] args) throws Exception {

    // 폴더와 파일을 한 번에 생성하는 방법
    File file = new File("temp2/a/b/c/test.txt");

    // 파일의 디렉토리 경로를 가지고 File 객체 생성
    File dir = new File(file.getParent()); // getParent() test.txt이 속해있는 상위 디렉토리를 알려준다.
     System.out.println(dir.getName());
     //System.out.println(dir.getPath());
    // System.out.println(dir.getCanonicalPath());

    // File dir = file.getParentFile();
    // System.out.println(dir.getName());
    // System.out.println(dir.getPath());
    // System.out.println(dir.getCanonicalPath());
    
// 먼저 디렉토리를 생성한다.
     if (dir.mkdirs()) { //상위폴더를 먼저 알아내고, mkdirs() 해야지 그 상위폴더들이 다~ 만들어 진다.
     System.out.println("디렉토리를 생성함.");
     } else {
     System.out.println("디렉토리를 생성하지 못함.");
     }
    
     if (file.createNewFile()) {
     System.out.println("파일을 생성함.");
     } else {
     System.out.println("파일을 생성하지 못함.");
     }
  }
}


