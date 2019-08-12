package com.eomcs.lms.servlet;
//BoardServlet는 클라이언트와 소통이 가능하다. DAO는 기능만!
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.Servlet;
import com.eomcs.lms.dao.BoardSerialDao;
import com.eomcs.lms.domain.Board;


public class BoardServlet implements Servlet {

  BoardSerialDao boardDao; // ArrayList를 개체로 포장하였다.

  ObjectInputStream in;
  ObjectOutputStream out;

  public BoardServlet(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    this.in = in;
    this.out = out;

    boardDao = new BoardSerialDao("./board.ser");
  }

  public void saveData() {
    boardDao.saveData();
  }

  @Override
  public void service(String command) throws Exception {
    switch (command) {

      case "/board/add":
        addBoard();
        break;

      case "/board/list":
        listBoard();
        break;

      case "/board/delete":
        deleteBoard();
        break;

      case "/board/detail":
        detailBoard();
        break;

      case "/board/update":
        updateBoard();
        break;

      default:
        out.writeUTF("Fail");
        out.writeUTF("지원하지 않는 명령입니다.");
    }

  }


  private void updateBoard() throws Exception {

    Board board = (Board) in.readObject();
    if (boardDao.update(board) == 0) {
      fail("해당 번호의 게시물이 없습니다.");
      return;
    }
    out.writeUTF("ok");
  }

  private void detailBoard() throws Exception {
    int no = in.readInt();

    Board board = boardDao.findBy(no);
    if (board == null) {
      fail("해당 번호의 게시물이 없습니다.");
      return;
    }
    out.writeUTF("ok");
    out.writeObject(board);

  }

  private void deleteBoard() throws Exception {
    int no = in.readInt(); // (2)를 읽는다.

    if (boardDao.delete(no) == 0) {
      fail("해당 번호의 게시물이 없습니다.");
      return;
    }
    out.writeUTF("ok");

  }

  private void addBoard() throws Exception {

    Board board = (Board) in.readObject();
    if (boardDao.insert(board) == 0) {
      fail("해당 번호의 게시물이 없습니다.");
      return;
    }
    out.writeUTF("ok");

  }

  private void listBoard() throws Exception {

    out.writeUTF("ok");
    out.reset(); // 기존의 serialize 했던 객체의 상태를 무시하고 다시 serialize 한다.
    out.writeObject(boardDao.findAll());
  }


  private void fail(String cause) throws Exception {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }

}
