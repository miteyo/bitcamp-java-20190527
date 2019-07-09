// 메서드 - 가변 파라미터 vs 배열 레퍼런스
package ch06;

public class Test07 {
  public static void main(String[] args) {
    // 가변 파라미터는 호출하는 쪽에서 배열을 만들 필요가 없다.
    // 자바가 자동으로 배열을 만든다.
    
    //plus(new int[] {}) 자동으로 배열이 만들어짐
    
    plus1(); // =>  plus1(new int[] {})      컴파일할 때 이렇게 바꿔줌.
    plus1(10); //=> plus1(new int[] {10})
    plus1(10, 20); // plus1(new int[] {10, 20})

    plus1(new int[] {});
    plus1(new int[] {10});
    plus1(new int[] {10, 20});
//모두 가능.


    // 배열 레퍼런스는 호출자가 만들어서 넘겨야 한다. 그래서 호출할 때는 가변 파라미터가 편하다.
    plus2(new int[] {});

    plus2(new int[] {10});
    plus2(new int[] {10, 20});


  }

  // 가변 파라미터
  static int plus1(int... value) { // 주소변수 //값을 안줘도 되고, 값을 1개, 2개 .....가변적 //내부적으로 배열
    int sum = 0;
    for (int i = 0; i < value.length; i++) { //배열주소의.length
      sum += value[i];
    }
    return sum;
  }

  // 배열 레퍼런스 파라미터
  static int plus2(int[] value) { // 배열을 자동으로 안만든다.배열의 주소를 원한다.
    int sum = 0;
    for (int i = 0; i < value.length; i++) {
      sum += value[i];
    }
    return sum;
  }
}


