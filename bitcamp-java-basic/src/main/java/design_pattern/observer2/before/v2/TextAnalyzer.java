package design_pattern.observer2.before.v2;
//리더로 부터 데이터를 읽어요.
import java.io.Reader;

public class TextAnalyzer {
  Reader in;

  public TextAnalyzer(Reader in) {
    this.in = in;
  }

  public void execute() { //리더에서 하나씩 읽어옵니다.
    try {
      int ch;
      int count = 0;
      int totalLine = 0;
      boolean isEmpty = true;
      
      int totalLinecomment = 0;
      boolean startLinecomment = false;
      int countSlash = 0;
      
      while((ch = in.read()) != -1) { //char ch를 할 경우 -1을 저장하지 못한다. 그래서 int로 받는다.
        count++;
        
        if(ch == '\n') {
          totalLine++;
          isEmpty = true;
        } else {
          isEmpty = false;
        }
        
        if(!startLinecomment) {
          if(ch =='/') {
            if(countSlash == 0) {
              countSlash++; // 1개
            } else {
              totalLinecomment++; //1개
              startLinecomment = true;
            }
          
          } else {
            countSlash = 0;
          }
          
        } else if( ch == '\n') {
          startLinecomment = false;
        }

        
        if(!isEmpty) {
          totalLine++;
        }
        
      }       
      
      System.out.printf("총 읽은 문자 수: %d\n", count);
      
    } catch (Exception e) {
      System.out.println("분석 중 오류 발생!");
      
    } finally {
      // 주의! 
      // => 자원 해제는 그 자원을 관리하는 객체가 책임져야 한다.
      // => TextAnalyzer는 단지 Reader 자원을 생성자에서 받아서
      //    execute()에서 사용할 뿐이다.
      // => 따라서 다음과 같이 사용이 끝났다고 해서 여기서 자원을 해제해서는 안된다.
      // => 이 객체에 자원을 넘겨준 놈이 자원 해제의 책임을 져야 한다.
      
      // try {in.close();} catch(Exception e) {}
    }

  }

}
