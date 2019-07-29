// java.io.File 클래스 : 연습 과제 - bin/main 폴더를 뒤져서 모든 클래스 파일의 이름을 출력하라.
package ch22.a;

import java.io.File;

public class Test14_pra {

  public static void main(String[] args) throws Exception {
    // 클래스 이름을 출력할 때 패키지 이름을 포함해야 한다.
    // 예) ch01.Test01
    // 예) ch22.a.Test14
    //
    File dir = new File("bin/main");
    findClass2(dir, "");
    System.out.println("완료!");
  }

  
  static void findClass(File path, String packageName) { 
    
    if(path.isFile()) {
      String name = String.format("%s.%s", packageName,path.getName().replace(".class",""));
    }
    
    
    
    
    
    
    
  }
  
  static void findClass2(File path, String packageName) { //main 밑에 있에 있는 class는 패키지 없는 무소속 class

    // 1) path의 하위 디렉토리와 파일 목록을 얻는다.
    // => 단, 필터를 이용하여 디렉토리와 클래스파일(.class) 목록만 추출한다.
    File[] files = path.listFiles(f -> f.isDirectory() || f.getName().endsWith(".class"));

    // 2) 하위 디렉토리와 파일 목록에서 클래스를 찾는다.
    for (File file : files) {
      if (file.isDirectory()) //디렉토리면
        findClass(file, packageName + "." + file.getName());
      else { //파일이면
        System.out.println(String.format("%s.%s", 
                            packageName,
                          file.getName().replace(".class", "")).substring(1));
      }
    }
  }
  
}


