// 기능 확장하기 - Score 클래스를 상속 받아 기능 추가하기 II
package ch13.b;

import ch13.Score;

public class Test03 {
  public static void main(String[] args) {
    
    // Score3를 이용하여 수퍼 클래스의 기능을 호출할 수 있다.
    // => Score2나 다를 바가 없다. 다만 Score3는 compute()를 오버라이딩 했다.
    Score3 s = new Score3();
    s.setName("홍길동"); // Score의 메서드                    슈퍼클래스의 this에넌 Score3() 의 레퍼런스 주소가 들어간다.
    s.setKor(100); // Score의 메서드                            this에 주어진 객체가 !Score3의 객체! 이기 때문에 setKor이 Score이더라도 !Score3!의 compute() 메소드가 호출된다.
    s.setEng(100); // Score의 메서드
    s.setMath(100); // Score의 메서드       
    s.setMusic(100); // Score3의 메서드
    s.setArt(100); // Score3의 메서드
    System.out.printf("%s: %d(%.1f)\n", s.getName(), s.getSum(), s.getAver());
    
//"Score.compute() 호출됨!" 왜 안될까?
    
    System.out.println("---------------------------------");
    
    Score s2 = new Score();
    s2.setName("임꺽꺽꺽");
    s2.setKor(90);
    s2.setEng(90);
    s2.setMath(90);
    System.out.printf("%s: %d(%.1f)\n", s2.getName(), s2.getSum(), s2.getAver());
    
    
    
  }
}
