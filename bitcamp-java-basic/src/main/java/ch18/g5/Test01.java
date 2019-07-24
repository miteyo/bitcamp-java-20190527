//인터페이스와 디폴트 메소드
package ch18.g5;
import ch18.g2.FilmPrinter;
import ch18.g2.PaperPrinter;
import ch18.g2.Printer;

public class Test01 {

  public static void main(String[] args) {
    //신규 프로젝트에서 피룡한 워터마킹 기능을 기존 클래스에 영향을 주지 않으면서, 기존 규격에 포함시키는 방법
    // => 디폴트 메소드를 추가하라!
    // => g2 패키지의 Printer 인터페이스에 watermark()메서드를 
    //    디폴트 매서드로 선언하면 된다.
    
    //프린터 준비
    WaterMarkPrint p1 = new WaterMarkPrint();
    display(p1, "안녕하세요오~", "bitcamp bitcamp");

    // 기존 프린터 준비!
    PaperPrinter p2 = new PaperPrinter();
    FilmPrinter p3 = new FilmPrinter();
    
    //기존 규격에 새 규칙(wartermark())를 추가하더라도 기존에 작성한 클래스를 그대로 활용할 수 있다.
    //굳이 g4 패키지에서 한 것 처럼 아답터를 사용할 필요도 없다.

    display(p2, "안녕하세요오~", "bitcamp bitcamp");  // display(Printer2 => 새 규격을 이요한다.
    display(p3, "안녕하세요오~", "bitcamp bitcamp");
      
  }

  private static void display(Printer printer, String text, String watermarkText) { //기존 규격을 사용하면 된다.
    printer.watermark(watermarkText); // 기존 규격에 새로 추가한 메소드
    printer.print(text);
    printer.watermark(watermarkText); // 기존 규격에 새로 추가한 메소드
  }
  
}
