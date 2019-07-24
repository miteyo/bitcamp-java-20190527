//새로 만든 Print2는 사용할 수 있지만 기존의 Print는 사용 할 수 없다.-->해결방법: g4패키지
package ch18.g3;
import ch18.g2.FilmPrinter;
import ch18.g2.PaperPrinter;

public class Test01 {

  public static void main(String[] args) {
    //신규 프로젝트에서는 워터마킹 기능이 있는 프린터를 사용한다.
    
    //프린터 준비
    WaterMarkPrint p1 = new WaterMarkPrint();
    display(p1, "안녕하세요오~", "bitcamp bitcamp");

    // 기존 프린터 준비!
    PaperPrinter p2 = new PaperPrinter();
    FilmPrinter p3 = new FilmPrinter();
    //안타깝게도 기존프린터는 새 프린트 규격에 맞지 않아서 출력하는데 사용할 수 없다
    // =>컴파일 오류!
    //display(p2, "안녕하세요오~", "bitcamp bitcamp");  // display(Printer2 => 새 규격을 이요한다.
    //display(p3, "안녕하세요오~", "bitcamp bitcamp");
      
  }

  private static void display(Printer2 printer, String text, String watermarkText) {
    printer.watermark(watermarkText);
    printer.print(text);
    printer.watermark(watermarkText);
  }
  
}
