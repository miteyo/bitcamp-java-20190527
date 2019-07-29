// primitive data type의 값을 출력하는 것을 도와 줄 
package ch22.c.ex2.byte_stream;

public class Test01_2 {
  public static void main(String[] args) throws Exception {
    //FileOutputStream는 배열이나 int 값만 가능.. long short는 비트를 옮겨서 가능

    DataOutputStream out = new DataOutputStream("temp/data.bin");

    short s = 0x1122;
    int i = 0x33445566;
    long l = 0x1122334455667788L;
    String str = "ABC가각간";
    boolean b = true;

    out.writeShort(s);
    out.writeInt(i);
    out.writeLong(l);
    out.writeUTF(str);
    out.writeBoolean(b);

    out.close();
    System.out.println("출력 완료!");
  }
}
