 // DataInputStream + 버퍼기능 = DataInputStream2
package ch22.c.ex5.byte_stream;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;


public class Test02_2 {
  public static void main(String[] args) throws Exception {
    //파일에서 데이터를 읽는 일을 할 객체를 준비한다.
    FileInputStream other = new FileInputStream("temp/data.bin"); //3) 데이터 8000바이트 줘봐
    
    // 위 객체에 버퍼링 기능을 붙인다.
    BufferedInputStream other2 = new BufferedInputStream(other); //2) 데이터 8개좀 줘봐 4) 8000바이트 받아놓고
    
    //위 객체에 primitive 타입의 값을 읽는 기능을 붙인다.
    DataInputStream in = new DataInputStream(other2); //1) long 값줘봐. 5) long 변수 리턴
    

    System.out.println("읽기시작...");
    long start = System.currentTimeMillis();
    
    for(int cnt = 0; cnt < 100000; cnt ++) {
      
    // 바이너리 데이터를 읽을 때는 저장한 순서(파일 포맷)에 맞춰 읽어야 한다.
    short s = in.readShort();
    int i = in.readInt();
    long l = in.readLong();
    String str = in.readUTF();
    boolean b = in.readBoolean(); 
    }
    
    long  end = System.currentTimeMillis();
    System.out.println(end - start);
    
    in.close();
    
    System.out.println("읽기 완료!");
  }
}
