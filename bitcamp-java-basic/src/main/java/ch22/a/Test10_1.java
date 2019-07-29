// java.io.File 클래스 : 필터 사용하기
package ch22.a;

import java.io.File;
import java.io.FilenameFilter;

public class Test10_1 {

  // static nested class
  static class TextFileFilter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
      System.out.printf("%s => %s\n", dir.getName(), name);
      return name.endsWith(".txt");    
    }
  }
  
  public static void main(String[] args) throws Exception {
    
    File file = new File(".");
    
    String[] names = file.list(new TextFileFilter()); //file.list 파일 목록을 리턴 ->list가 자기가 작업할 때 new TextFileFilter() 넘겨준 것을 이용한다.
    
    for (String name : names) {
      System.out.println(name);
    }
  }    
}


