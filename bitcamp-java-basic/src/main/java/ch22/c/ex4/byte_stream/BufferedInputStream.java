package ch22.c.ex4.byte_stream;

import java.io.IOException;
import java.io.InputStream;

// 데코레이터 설계 패턴을 적용하여 기존 InputStream에 기능을 추가한다.
// => DecoratorInputStream을 상속받는다.
public class BufferedInputStream extends DecoratorInputStream {
  
  byte[] buf = new byte[8192];
  int size = 0;
  int cursor = 0;
  int count = 0;
  
  public BufferedInputStream(InputStream other) throws IOException { //다른 InputStream을 받아서,
    super(other);
  }
  
  @Override
  public int read() throws IOException {        //read()를 호출하면 , 생성자로 부터 받은 stream의 read() 호출
    if (cursor >= size) { 
      count++;
      size = other.read(buf); 
      if (size == -1) 
        return -1;
      cursor = 0; 
    }
    return buf[cursor++] & 0x000000ff; 
  }
  
}






