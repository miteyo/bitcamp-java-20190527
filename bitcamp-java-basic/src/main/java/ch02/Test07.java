// 문자 리터럴
package ch02;

public class Test07 {
  public static void main(String[] args) {
    // '가' 문자를 출력하라
    System.out.println(0xac00); // UTF-16 코드 값을 지정한다.
    // 해당 숫자가 UTF-16의 문자 코드임을 알려주지 않으면 println()은 
    // 그냥 일반 숫자인 줄 알고 10진수로 출력한다.
    
    // 해결책?
    // => 숫자 앞에 (char) 를 붙여 이 숫자가 평범한 숫자가 아니라
    //    문자의 UTF-16 코드 값임을 알려줘라.
    // => UTF-16은 2바이트 크기를 갖는다. 
    // => 코드의 값은 0 ~ 65535 이다.
    // => 음수가 없다.
    // => 참고! 숫자 2바이트의 범위는 -32768 ~ 32767 이다.
    System.out.println((char)0xac00);
    
    //흥 이라는 문자의 코드값을 ''사이에 놓아라
    long v = '흥';
    System.out.println(v);
    System.out.println((char)v);
    System.out.println('흥');
    
    // 문자의 코드를 알지 못한다면 작은 따옴표('')를 이용할 수 있다.
    // '가'?
    // 한글 '가'의 UTF-16 값을 리턴한다.
    // 또한 이 값이 문자의 코드 값임을 알려준다.
    
    System.out.println('가');
    
    System.out.println('가' + 2);
    System.out.println("(기준) " + (char)('가' + 2));
    
    // 작은 따옴표 안에 직접 유니코드(UTF-16)를 표기할 수 있다. /u: ac00 문자코드니까 폰트파일에서 찾아서 출력해
    System.out.println('\uac00');
    
    // 작은 따옴표 안에 직접 유니코드를 지정하는 경우?
    // => 특수 문자를 출력하고 싶을 때
    // => 코드에 해당하는 문자를 콘솔 창에 출력할 때는 
    //    콘솔 창에서 사용하는 폰트에서 해당 문자를 찾는다.
    // => 즉 사용하는 폰트가 무엇이냐에 따라 출력하는 문자의 모양이 다를 수 있다.
    System.out.println('\u32d4');
    
  }
}

//# 문자를 컴퓨터에 저장하는 방법
//- 2진수화(인코딩; encoding) 시켜서 저장한다.
//
//# 문자를 2진수로 변환하는 규칙 => 문자집합(character set) 
//1) ASCII (7bit) 
//- 미국 국가 표준 협회(ANSI)에서 영어 알파벳, 숫자, 특수 문자 등을 컴퓨터에서 저장할 수 있도록
//  7비트 2진수로 정의한 인코딩 규칙
//- A(0x41), B(0x42), a(0x61), b(0x62), 스페이스(0x20), LF(0x0a), CR(0x0d), 
//  !(0x21), #(0x23), $(0x24), %(0x25), &(0x26), +(0x2b),
//  0(0x30), 1(0x31), =(0x3d), ?(0x3f), @(0x40) 등.
//- 참고
//  윈도우 OS에서는 LFCR 두 바이트를 사용하여 줄 바꿈을 표시한다.
//  macOS, Linux, Unix 에서는 LF 1바이트만 사용하여 줄 바꿈을 표현한다.
// 
//2) ISO-8859-1(ISO-latin-1)
//- ASCII 문자에 유럽 문자까지 포함하는 국제 표현 문자 인코딩 규칙이다.
//- 가장 널리 쓰이며 대부분의 서유럽 언어를 지원한다. 
//  네덜란드어(일부[* 1]), 노르웨이어, 덴마크어, 독일어, 로만슈어, 스웨덴어 스코틀랜드 게일어, 
//  아이슬란드어, 아일랜드어, 에스파냐어, 영어, 이탈리아어, 페로어, 포르투갈어, 
//  프랑스어(일부[* 2]), 핀란드어(일부[* 2]), 동유럽의 알바니아어, 아프라카의 스와힐리어, 
//  아프리칸스어. 유로 기호(€)와 대문자 "Ÿ"는 개정판인 ISO 8859-15에 추가되었다. 
//  IANA의 문자 집합 ISO-8859-1에 대응하며, HTML 문서의 기본 인코딩이다.
//
//3) EUC-KR(2바이트; 한글 2560자 + 한자 등 포함)
//- 국제 표준이다.
//- 한글 음절 한 개에 대해 인코딩 규칙을 정의한 완성형 인코딩 규칙이다.
//- 현재 유통되는 모든 한글 음절을 표현할 수 없다.
//- 가(0xb0a1), 각(0xb0a2), 똘(0xb6ca), 똠(?), 똥(0xb6cb) 등 
//
//4) 조합형(2바이트; 한글(1) + 초성(5비트) + 중성(5비트) + 종성(5비트))
//- 국제 표준이 아니다.
//- 초성: ㄱ(00010), ㄲ(00011), ㄴ(00100), ㄷ(00101), ...
//- 중성: ㅏ(00011), ㅐ(00100), ㅑ(00101), ㅒ(00110), ...
//- 종성: ㄱ(00010), ㄲ(00011), ㄱㅅ(00100), ㄴ(00101), ... 
//- 예) 꺅(1000 1100 1010 0010 = 0x8ca2)
//
//5) MS949(=CP949; 2바이트)
//- 윈도우 OS의 인코딩 규칙이다.
//- EUC-KR + 현재 유통되는 한글 음절 추가 = MS949 (11172자 + 한국 전용 한자 + 옛한글 등)
//- MS949에 추가된 문자
//  똠(0x8c63), 똡(0x8c64), 등
//- 기존의 EUC-KR에 문자를 추가했기 때문에 정렬할 때 문제가 발생한다.
//  "똘똠똡똥(b6ca 8c63 8c64 b6cb)" 문자를 정렬한다면,
//  숫자 크기에 따라 "똠똡똘똥"으로 나열될 것이다.
//
//6) Unicode(2바이트; UTF-16 = ISO 10646)
//- 국제 표준 인코딩 규칙이다.
//- 영어, 한글 모두 2바이트로 인코딩한다.
//- MS949의 모든 문자가 포함되어 있다.
//- A(0x0041), B(0x0042), +(0x002b), 가(0xac00), 각(0xac01), ...
//- 기존의 EUC-KR과 MS949 코드와 호환되지 않는다.
//- JVM은 내부에서 문자열을 다룰 때 UTF-16을 사용한다.
//  즉 영어, 한글 모두 2바이트 유니코드로 다룬다.
//
//7) UTF-8(1~4바이트; Unicode Transformation Format)
//- Unicode에서 영어권 문자를 그대로 1바이트로 인코딩하기 위해 탄생함.
//- Unicode(UTF16)을 그대로 사용하면, 기존의 OS를 모두 변경해야 한다.
//  왜? 기존의 OS는 ISO-8859-1 규칙에 따라 영어를 다루기 때문이다.
//  한,중,일 처럼 2바이트를 사용하는 문자를 다루기 위해 전 세계의 모든 컴퓨터의 운영체제를 
//  바꿔야 한다면 누가 그 비용을 감당할 것인가!
//  그래서 영어권 PC는 Unicode를 사용하더라도 변경할 필요가 없도록 UTF8을 만들었다.
//- 규칙
//  0000 ~ 007F(7bit ASCII) : 이전과 똑 같이 1바이트로 인코딩한다.
//  0080 ~ 07ff : 2바이트로 인코딩한다. 
//                00000xxx xxxxxxxx --> 110xxxxx 10xxxxxx
//  0800 ~ ffff : 3바이트로 인코딩한다. 
//                xxxxxxxx xxxxxxxx --> 1110xxxx 10xxxxxx 10xxxxxx
//  010000 ~ 10ffff : 4바이트로 인코딩한다.
//- 한글은(ac00 ~ d7af) 0800 ~ ffff 에 속하기 때문에 3바이트로 변환한다.
//- UTF-16 '가(0xac00)'를 UTF-8로 인코딩하기
//  '가' => 1010 1100 0000 0000 (UTF-16) 
//      => 1110 1010 1011 0000 1000 0000 (UTF-8)
//      => 0xEAB080
// 




















