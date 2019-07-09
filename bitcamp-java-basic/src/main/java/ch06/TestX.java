package ch06;

public class TestX {

  public static void main(String[] args) {
   
    String name = System.getProperty("name");
    int kor = Integer.parseInt(System.getProperty("kor"));
    int eng = Integer.parseInt(System.getProperty("eng"));
    int math = Integer.parseInt(System.getProperty("math"));
   
    int sum = (kor + eng + math);
    
    System.out.println("이름  : " + name);
    System.out.println("총점 : " + sum);
    System.out.println("평균: " + sum/3);
    

  }

}
