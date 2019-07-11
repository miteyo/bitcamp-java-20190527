package ch06;

public class TestX {

  public static void main(String[] args) {
    


     System.out.printf("이름: %s\n", args[0]);
    
     int sum = 0;
     for(int i = 1; i<args.length; i++) {
     sum += Integer.parseInt(args[i]);
     }
     System.out.printf("합계는: %d\n",sum);
     System.out.printf("평균은: %.2f\n" , (float)sum/(args.length-1));
    

    String name = System.getProperty("name");
    int kor = Integer.parseInt(System.getProperty("kor"));
    int eng = Integer.parseInt(System.getProperty("eng"));
    int math = Integer.parseInt(System.getProperty("math"));

    int sum1 = kor + eng + math;
    System.out.println("이름 : " + name);
    System.out.println("총점 : " + sum1);
    System.out.println("평균 " + sum1 / 3);

  }
}
