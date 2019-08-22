package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoFile;

public class PhotoFileDaoImpl implements PhotoFileDao {
  Connection con;


  public PhotoFileDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public int insert(PhotoFile photoFile) throws Exception {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate("insert into lms_photo_file(photo_id,file_path)" + " values("
          + photoFile.getBoardNo() + ",'" + photoFile.getFilePath() + "')");
    }
  }

  @Override
  public List<PhotoFile> findAll(int boardNo) throws Exception {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select photo_file_id, photo_id, file_path"
            + " from lms_photo_file"
            + " where photo_id=" + boardNo
            + " order by photo_file_id asc")) {

      // 데이터 리턴해주기
      ArrayList<PhotoFile> list = new ArrayList<>();
      while (rs.next()) {
        PhotoFile photoFile = new PhotoFile();
        photoFile.setNo(rs.getInt("photo_file_id")); // 각각의 항목에 db컬럼값을 채운다.
        photoFile.setBoardNo(rs.getInt("photo_id"));
        photoFile.setFilePath(rs.getString("file_path"));
        list.add(photoFile);
      }
      return list;
    }
  }

 

  @Override
  public int deleteAll(int boardNo) throws Exception {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate("delete from lms_photo_file where photo_id=" + boardNo);
    }
  }



  public static void main(String[] args) throws Exception {

    try (Connection con = DriverManager
        .getConnection("jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {

      PhotoFileDao dao = new PhotoFileDaoImpl(con);

      // 1) insert 테스트
//      PhotoFile b = new PhotoFile();
//       b.setBoardNo(60);
//       b.setFilePath("ok4.gif");
//      
//       dao.insert(b);
//       System.out.println("실행완료");

      // 2) findAll() 테스트
      List<PhotoFile> list = dao.findAll(6);
      for(PhotoFile p : list) {
        System.out.println(p);
      }
      System.out.println("실행완료!");


      //3)delete
      //dao.deleteAll(6);
    }
  }
}
