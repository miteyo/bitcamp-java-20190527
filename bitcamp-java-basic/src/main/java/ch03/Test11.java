// 배열 - 선언하는 방법과 사용하는 방법
package ch03;

public class Test11 {
  public static void main(String[] args) {
    // 배열 문법을 사용하지 않고 같은 종류의 값을 여러 개 저장하는 방법
    // => 저장할 값 개수 만큼 변수를 선언해야 한다.
    int a1, a2, a3, a4, a5;
    a1 = 100;
    a2 = 90;
    a3 = 80;
    a4 = 70;
    a5 = 60;
    
    // 배열 문법을 이용하여 같은 종류의 메모리를 여러 개 만드는 방법
    // # C 언어
    // => C에서는 배열을 만들 때 개수를 바로 지정한다.
    //int arr[5]; // 자바는 이런 문법을 지원하지 않는다. 컴파일 오류!
    
    // # 자바
    // 1) 배열을 가리킬 레퍼런스 선언
    // => 레퍼런스(reference)? 메모리의 주소를 저장하는 변수
    int[] arr1; // 자바는 이 형식을 선호한다.
    int arr2[]; // c 언어 스타일도 사용할 수 있다.

    // 자바에서는 로컬 변수(메서드 안에 선언된 변수)에 값을 저장하지 않고 사용하려 시도하면
    // 컴파일 오류가 발생한다.
    //arr1[0] = 100; // 초기화 하지 않고 사용했기 때문에 컴파일 오류 발생
    
    arr1 = new int[3]; //null; // null? 0으로 설정한다. 즉 특정 메모리를 가리키지 않는 상태를 표시함.
    arr1[3] = 100; // arr1을 사용하기 전에 초기화 시켰기 때문에 컴파일 오류는 발생하지 않는다.
                  // 그러나 없는 배열을 사용하려고 했기 때문에 
                  // NullPointerException 예외 발생
    
    // 배열을 만들지도 않고, 레퍼런스를 사용하려 하면 오류!
    
    // 2) 배열을 생성하고 그 주소를 레퍼런스에 담는다.
    //    생성된 배열을 "배열 인스턴스"라고 부른다.
    arr1 = new int[5];
    arr2 = new int[5];
    
    // 3) 배열에 값을 담기 - 배열 레퍼런스를 사용하여 메모리를 지정한다.
    arr1[0] = 100;
    arr1[1] = 90;
    arr1[2] = 80;
    arr1[3] = 70;
    arr1[4] = 60;
    //arr1[-1] = 100; // ArrayIndexOutOfBoundsException 예외 발생
    //arr1[5] = 50; // ArrayIndexOutOfBoundsException 예외 발생
    
  }
}

/*
# 배열
- 같은 종류의 메모리를 여러 개 만드는 문법

# 배열 선언
  메모리종류[] 레퍼런스변수명;
  레퍼런스변수명 = new 메모리종류[개수];
  
  데이터타입[] 레퍼런스;
  레퍼런스 = new 데이터타입[개수];

예) int 메모리를 5개 선언하라.
  int[] values;
  values = new int[5];
  
# 배열 사용
  배열레퍼런스[인덱스] = 값;
- 인덱스는 0부터 시작하여 배열 개수 보다 한 개 작은 값을 갖는다.
  예) int[] arr = new int[10];
  인덱스: 0 ~ 9
  
 */














