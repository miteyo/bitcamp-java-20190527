package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardDaoImpl implements BoardDao {
  Connection con;


  public BoardDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public int insert(Board board) throws Exception {
    try (Statement stmt = con.createStatement()) {

      return stmt
          .executeUpdate("insert into lms_board(conts)" + " values('" + board.getContents() + "')");
    }
  }

  @Override
  public List<Board> findAll() throws Exception {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from lms_board order by board_id desc")) {

      // 데이터 리턴해주기
      ArrayList<Board> list = new ArrayList<>();
      while (rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("board_id")); // 각각의 항목에 db컬럼값을 채운다.
        board.setContents(rs.getString("conts"));
        board.setCreatedDate(rs.getDate("cdt"));
        board.setViewCount(rs.getInt("vw_cnt"));
        list.add(board);
      }
      return list;
    }
  }

  @Override
  public Board findBy(int no) throws Exception {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from lms_board where board_id=" + no)) {

      if (rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("board_id")); // 각각의 항목에 db컬럼값을 채운다.
        board.setContents(rs.getString("conts"));
        board.setCreatedDate(rs.getDate("cdt"));
        board.setViewCount(rs.getInt("vw_cnt"));

        //게시글을 찾았으면 조회수를 증가시킨다.
        stmt.executeUpdate("update lms_board set"
            + " vw_cnt=vw_cnt+1 where board_id=" + no);
        return board;

      } else {
        return null;
      }
    }
  }

  @Override
  public int update(Board board) throws Exception {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate("update lms_board set "
          + " conts='" + board.getContents()
          + "' where board_id=" + board.getNo());
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate("delete from lms_board where board_id=" + no);
    }
  }
}
