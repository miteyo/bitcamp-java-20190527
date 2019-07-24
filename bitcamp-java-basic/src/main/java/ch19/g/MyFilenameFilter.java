
package ch19.g;

import java.io.File;
import java.io.FilenameFilter;

// File.list() 메서드에서 사용할 FilenameFilter 를 만든다.
//
public class MyFilenameFilter implements FilenameFilter { //이 규칙에 정의 된 메소드를 구현한다.

  @Override // 우리가 호출 하는 것이 아니라, list가 프로그램 내에서 호출
  public boolean accept(File dir, String name) {
    if (name.endsWith(".txt"))
      return true;
    else 
      return false;
  }

}










