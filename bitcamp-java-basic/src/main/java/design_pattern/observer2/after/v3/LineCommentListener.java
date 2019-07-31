package design_pattern.observer2.after.v3;
//분석기로 부터 문자 하나를 읽을 때 마다 보고를 받는다.
// 그리고 한 줄 주석을 찾아 개수를 센다.
// 분석기로 부터 보고를 받으려면 규칙에 따라 클래스를 정의해야 한다.
public class LineCommentListener implements CharacterListener {

  int lines = 0;
  boolean isStartComment = false;
  int countSlash = 0;
  
  @Override
  public void readed(int ch) {
    if(!isStartComment) { //주석이 시작되지 않을때 
      if(ch =='/') { 
        if(countSlash == 0) {
          countSlash++;

        } else {
          lines ++;
          isStartComment = true;
        }
      } else {
        countSlash = 0; // 10/2인 경우 다음이 /가 아니라 2니까 초기화. 연속으로 나올 때만 출력해야함.
      }
    } else if(ch == '\n') {
      isStartComment = false;
    }
  }

  @Override
  public void displayResult() {
    System.out.printf("한 줄 주석 개수: %d\n", lines);
  }

}
