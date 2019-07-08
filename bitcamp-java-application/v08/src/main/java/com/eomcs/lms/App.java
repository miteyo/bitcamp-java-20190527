// 애플리케이션 Main 클리스
// =>애플리케이션을 실행할 때 이 클래스를 실행한다.
package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner; // 컴파일러에게 알려주는 것.

public class App {
  static Scanner keyScan;

  public static void main(String[] args) {
    java.io.InputStream keyboard = System.in;
    keyScan = new Scanner(keyboard);

    Lesson[] lessons = new Lesson[100]; // ,레퍼런스배열 100개를 만들어놈.
    int lessonsSize = 0;

    Member[] members = new Member[100];
    int membersSize = 0;

    Board[] boards = new Board[100];
    int boardsSize = 0;

    while (true) {

      System.out.print("명령> ");
      String command = keyScan.nextLine();

      if (command.equals("quit")) {
        break;

      } else if (command.equals("/lesson/add")) { // 파라미터로 Object면 다 되는거야. (String, car, Date ...)

        Lesson lesson = new Lesson();

        lesson.no = getIntValue("번호? ");
        lesson.title = getStringValue("수업명? ");
        lesson.contents = getStringValue("설명? ");
        lesson.startDate = getDateValue("시작일? ");
        lesson.endDate = getDateValue("종료일? ");
        lesson.totalHours = getIntValue("총 수업시간? ");
        lesson.dayHours = getIntValue("일 수업시간?");

        lessons[lessonsSize++] = lesson;// 현재 size 0, 일단 0이 놓이고. lesson에서는 0이 우선 저장이되고(size는 1이 되었어도)
        System.out.println("저장하였습니다.");

      } else if (command.equals("/lesson/list")) {

        for (int i = 0; i < lessonsSize; i++) { // 현재 lesson 인스턴스 주소가 들어있는 배열 번호까지
          Lesson lesson = lessons[i]; // 레퍼런스 배열에서 한 개의 인스턴스 주소를 꺼낸다
          System.out.printf("%s, %s, %s, %s ~ %s, %s\n", lesson.no, lesson.title, lesson.contents,
              lesson.startDate, lesson.endDate, lesson.totalHours);
        }

      } else if (command.equals("/member/add")) {
        Member member = new Member();
        member.no = getIntValue("번호? ");
        member.name = getStringValue("이름? ");
        member.email = getStringValue("이메일? ");
        member.password = getStringValue("암호? ");
        member.photo = getStringValue("사진? ");
        member.tel = getStringValue("전화번호? ");
        member.registeredDate = getDateValue("가입일? ");

        members[membersSize++] = member;
        System.out.println("저장하였습니다.");

      } else if (command.equals("/member/list")) {
        for (int i = 0; i < membersSize; i++) {
          Member member = members[i]; 
          System.out.printf("%s, %s , %s, %s, %s\n", member.no, member.name, member.email,
              member.tel, member.registeredDate);
        }
      } else if (command.equals("/board/add")) {
        Board board = new Board();
        board.no = getIntValue("번호는? ");
        board.contents = getStringValue("내용은? ");
        board.createdDate = new Date(System.currentTimeMillis());

        boards[boardsSize++] = board;
        System.out.println("저장하였습니다.");
        
      } else if (command.equals("/board/list")) {
        for (int i = 0; i < boardsSize; i++) {
          Board board = boards[i];
          System.out.printf("%s, %s, %s, %s\n", board.no, board.contents, board.createdDate,
              board.viewCount);
        }

      } else {
        System.out.println("해당 명령을 지원하지 않습니다.");
      }
      System.out.println();
    }
  }



  private static int getIntValue(String message) {
    while (true) {
      try {
        System.out.print(message);
        return Integer.parseInt(keyScan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("숫자를  입력하세요.");
      }
    }
  }

  // copy/paste
  private static Date getDateValue(String message) {
    while (true) {
      try {
        System.out.print(message);
        return Date.valueOf(keyScan.nextLine());
      } catch (IllegalArgumentException e) {
        System.out.println("2019-07-05 형식으로  입력하세요.");
      }
    }
  }

  private static String getStringValue(String message) {
    while (true) {
      System.out.print(message);
      return keyScan.nextLine();
    }
  }

}

