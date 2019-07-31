package design_pattern.observer2.after.v1;

public class CharacterCountListener implements CharacterListener {

  int count = 0;

  @Override
  public void readed(int ch) {
    // 한 문자를 읽을 때 마다 count증가
    count++;
  }

  @Override
  public void displayResult() {
    System.out.printf("총 문자 개수: %d\n", count);
  }


}
