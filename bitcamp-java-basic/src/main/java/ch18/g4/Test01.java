package ch18.g4;

import ch18.g2.FilmPrinter;
import ch18.g2.PaperPrinter;
import ch18.g3.Printer2;
import ch18.g3.WaterMarkPrint;

public class Test01 {

  public static void main(String[] args) {
    //신규 프로젝트에서는 워터마킹 기능이 있는 프린터를 사용한다.
    
    //신규 규격에 맞춰 제작한 프린터를 사용하기
    WaterMarkPrint p1 = new WaterMarkPrint();
    display(p1, "안녕하세요오~", "bitcamp bitcamp");

    // 기존 프린터 준비!
    PaperPrinter p2 = new PaperPrinter();
    FilmPrinter p3 = new FilmPrinter();
    
    // 안타깝게도 기존프린터는 새 프린트 규격에 맞지 않아서 직접 사용할 수 없다 
    // 컴파일 오류!
    // 해결책! 
    //  => 어댑터에 꼽아 사용해 보자.
    display(new Printer2Adapter(p2), "안녕하세요오~", "어댑터어댑터");  // display(Printer2 요구하지만 p2,p3은 Print)
    display(new Printer2Adapter(p3), "안녕하세요오~", "bitcamp bitcamp");
      
  }

  private static void display(Printer2 printer, String text, String watermarkText) {
    printer.watermark(watermarkText);
    printer.print(text);
    printer.watermark(watermarkText);
  }
  
}
