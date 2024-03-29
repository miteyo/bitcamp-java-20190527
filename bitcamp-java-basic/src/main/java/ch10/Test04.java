// 인스턴스 필드의 초기화 - 초기화 문장을 통해 필드에 기본 값을 미리 저장한다. 
package ch10;

class Monitor2 {
  // 초기화 문장?
  // 변수를 선언할 때 값을 설정하는 것을 초기화 문장이라 부른다.
  // 
  int bright = 50; // 밝기 (0% ~ 100%)
  int contrast = 50; // 명암 (0% ~ 100%)
  int widthRes = 1920; // 해상도 너비
  int heightRes = 1080; // 해상도 높이
  
  void display() {
    System.out.println("----------------------------------");
    System.out.printf("밝기(%d)\n", this.bright);
    System.out.printf("명암(%d)\n", this.contrast);
    System.out.printf("해상도(%d x %d)\n", this.widthRes, this.heightRes);
    System.out.println("----------------------------------");
  }
}

public class Test04 {
  public static void main(String[] args) {
    // 모니터 인스턴스 생성
    Monitor2 m1 = new Monitor2();
    
    // 인스턴스 필드의 값이 유효한 기본 값들로 미리 초기화 되었기 때문에 바로 사용할 수 있다.
    m1.display(); 
    
    // 물론 특정 속성의 값을 바꾼 후에 사용해도 된다.
    m1.bright = 40;
    
    m1.display();
  }
}
















