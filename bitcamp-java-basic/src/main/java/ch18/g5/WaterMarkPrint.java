// 신규 프로젝트를 위해 기존 규격에 새로 규칙(watermark())를 추가 하였고
// 이 클래스는 그규칙은 그 규칙을 구현 하였다.
package ch18.g5;

import ch18.g2.Printer;

//패키지 g3처럼 새 규칙을 추가하기 위해 새 규격(interface)을 정의할 필요가 없다.
//""디폴트 메서드""라는 문법을 사용하면 기존 클래스에 영향을 주지않고 새 규칙을 추가 할 수 있다
public class WaterMarkPrint implements Printer {
  // Printer 인터페이스에 선언된 규칙을 구현한다.
  @Override
  public void print(String text) {
    System.out.println("WaterMarkPrinter: " + text);
  }
  
  //신규 프로젝트에 추가한 Print2 인터페이스 규칙을 구현한다.
  @Override
  public void watermark(String title) {
    System.out.println("**" + title + "**");
  }
}


//Print <<interface>> 
// print()
//default waterMark()

//print()는 강제 해야하지만 default가 붙은 waterMark는 강제 하지 않아도 된다.
//waterMark 기능이 필요 한 때에, 클래스를 만들고 waterMark 기능을 구현하면 된다.
