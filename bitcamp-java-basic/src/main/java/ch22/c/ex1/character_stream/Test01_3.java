// 버퍼 사용 - (for txt파일) 버퍼를 적용하여 데이터 읽기를 대신 처리해주는 도우미 클래스 사용
package ch22.c.ex1.character_stream;

public class Test01_3 {
  public static void main(String[] args) throws Exception {

    BufferedReader in = new BufferedReader("temp/jls12.txt");

    System.out.println("데이터 읽는 중...");

    long start = System.currentTimeMillis();

    int b;
    int count = 0;
    while ((b = in.read()) != -1) {// 1바이트씩 읽는다
      count++;
    }

    long end = System.currentTimeMillis();
    System.out.println(end - start);
    System.out.println(count);        // 1바이트를 33~만번 달라고 했다. 
    System.out.println(in.count);    // 파일 인풋을 이용하여 8192바이트 버퍼를 409번을 채웠다. 

    in.close();

    System.out.println("출력 완료!");
  }
}








