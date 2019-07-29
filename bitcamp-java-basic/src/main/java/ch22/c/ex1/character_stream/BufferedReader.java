package ch22.c.ex1.character_stream;

import java.io.FileReader;
import java.io.IOException;

//상속을 이용한 기능 추가
//기존의 FileInputStream에 버퍼링 기능을 추가하기
public class BufferedReader extends FileReader {
  
  char[] buf = new char[8192];
  int size = 0;
  int cursor = 0;
  int count = 0;
  
  public BufferedReader(String name) throws IOException {
    super(name);
  }
  
  @Override
  public int read() throws IOException {
    if (cursor >= size) { // 버퍼에 보관된 데이터를 다 읽었으면, 
      count++;
      size = read(buf); // 다시 상속 받은 메소드를 사용하여 8192바이트를 읽어온다. 
      if (size == -1) //물론 파일에 읽은데이터가 없다면, 즉 파일의 데이터를 다 읽었다면 -1을 리턴한다.
        return -1;
      cursor = 0; // 버퍼에 FileInputStream을 사용하여 새로 8192 바이트를 읽어 버퍼에 저장햇다면, 
                  // 다시 커서의 위치를 0으로 설정한다.
    }
    return buf[cursor++]; //비트 연산 &는 0과 &시키면 둘다 1일때만 1이니까 0과 1이 만나서 0이 된다. => 앞의 3바이트 값이 모두 0이됨
  }
  
}






