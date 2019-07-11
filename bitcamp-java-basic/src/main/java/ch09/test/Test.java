package ch09.test;

public class Test {

  public static void main(String[] args) {
    //계산하기 식1=> 2 * 3 - 2 + 7 = ?
    //      식2=> 6 / 2 + 4 = ?
    
    //Calculator 클래스에는 result 변수가 한개만 있기 때문에 두개의 계싼식을 동시에 수행할수 없다.
    //다음과 같이 한개의 식을 모두 계싼한 다음에 두번째 식을 계산해야 한다.
    
    // 해결책? 개별적으로 관리되어야하는 인스턴스 변수에 저장해야한다.
    
    
    //계산식1의 결과를 저장 할 메모리 생성
    Calculator calc1 = new Calculator(); //heap에 result가 만들어진다.
    
  //계산식2의 결과를 저장 할 메모리 생성
    Calculator calc2 = new Calculator();
    
    calc1.plus(2);      //메소드를 연산자로 생각하자.
    calc2.plus(6);
    
    calc1.multiple(3); //6  
    calc2.divide(2);
    
    
    calc1.minus(2); //4    
    calc2.plus(4);
    
    
    calc1.plus(7); //11
    
    System.out.printf("결과 = %d\n", calc1.result);
    System.out.printf("결과 = %d\n", calc2.result);
  }

}
