
package ch22.c.ex2.byte_stream;

public class Test02_2 {
  public static void main(String[] args) throws Exception {

    DataInputStream in = new DataInputStream("temp/data.bin");

    // 바이너리 데이터를 읽을 때는 저장한 순서(파일 포맷)에 맞춰 읽어야 한다.
    short s = in.readShort();
    int i = in.readInt();
    long l = in.readLong();
    String str = in.readUTF();
    boolean b = in.readBoolean(); 

    System.out.printf("%x, %x, %x, %s, %b\n", s, i, l, str, b);

    System.out.println("읽기 완료!");
  }
}








