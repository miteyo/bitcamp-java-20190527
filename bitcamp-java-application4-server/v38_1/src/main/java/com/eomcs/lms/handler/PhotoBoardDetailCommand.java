package com.eomcs.lms.handler;
import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.util.Input;

public class PhotoBoardDetailCommand implements Command {
  private PhotoBoardDao photoDao;

  public PhotoBoardDetailCommand(PhotoBoardDao photoDao) {
    this.photoDao = photoDao;
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {

    try {
      // 클라이언트에게 번호를 요구하여 받는다.
      int no = Input.getIntValue(in, out, "번호? ");
      PhotoBoard photo = photoDao.findBy(no);
      if (photo == null) {
        out.println("해당 번호의 데이터가 없습니다.");
        return;
      }

      out.printf("내용: %s\n", photo.getTitle());
      out.printf("작성일: %s\n", photo.getCreatedDate());
      out.printf("조회수: %d\n", photo.getViewCount());
      out.printf("수업: %d\n", photo.getLessonNo());
      

    } catch (Exception e) {
      out.println("데이터 조회에 실패했습니다.");
      System.out.println(e.getMessage());
    }
  }

}
