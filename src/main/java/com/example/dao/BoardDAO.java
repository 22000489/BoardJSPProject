package com.example.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.bean.BoardVO;
import com.example.util.JDBCUtil;

public class BoardDAO {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    private final String BOARD_INSERT = "insert into BOARD (firstName, lastName, birthday, gender, email, phone, information, userType, account, photo) values (?,?,?,?,?,?,?,?,?,?)";
    private final String BOARD_UPDATE = "update BOARD set firstName=?, lastName=?, birthday=?, gender=?, email=?, phone=?, information=?, userType=?, account=?, photo=? where id=?";
    private final String BOARD_SELECT = "select photo from BOARD where id=?";
    private final String BOARD_DELETE = "delete from BOARD  where id=?";
    private final String BOARD_GET = "select * from BOARD  where id=?";
    private final String BOARD_LIST = "select * from BOARD order by id desc";

    public int insertBoard(BoardVO vo) {
        System.out.println("===> JDBC로 insertBoard() 기능 처리");
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_INSERT);
            stmt.setString(1, vo.getFirstName());
            stmt.setString(2, vo.getLastName());
            stmt.setString(3, vo.getBirthday());
            stmt.setString(4, vo.getGender());
            stmt.setString(5, vo.getEmail());
            stmt.setString(6, vo.getPhone());
            stmt.setString(7, vo.getInformation());
            stmt.setString(8, vo.getUserType());
            stmt.setString(9, vo.getAccount());
            stmt.setString(10, vo.getPhoto());
            stmt.executeUpdate();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 글 삭제
    public void deleteBoard(BoardVO vo) {
        System.out.println("===> JDBC로 deleteBoard() 기능 처리");
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_DELETE);
            stmt.setInt(1, vo.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int updateBoard(BoardVO vo) {
        System.out.println("===> JDBC로 updateBoard() 기능 처리");
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_UPDATE);
            stmt.setString(1, vo.getFirstName());
            stmt.setString(2, vo.getLastName());
            stmt.setString(3, vo.getBirthday());
            stmt.setString(4, vo.getGender());
            stmt.setString(5, vo.getEmail());
            stmt.setString(6, vo.getPhone());
            stmt.setString(7, vo.getInformation());
            stmt.setString(8, vo.getUserType());
            stmt.setString(9, vo.getAccount());
            stmt.setString(10, vo.getPhoto());
            stmt.setInt(11, vo.getId());

            System.out.println(vo.getFirstName() + "-" + vo.getLastName() + "-" + vo.getBirthday() + "-" + vo.getGender() + "-" + vo.getEmail() + "-" + vo.getPhone() + "-" + vo.getInformation() + "-" + vo.getUserType() + "-" + vo.getAccount() + "-" + vo.getPhoto() + "-" + vo.getId());
            stmt.executeUpdate();
            return 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public BoardVO getBoard(int id) {
        BoardVO one = new BoardVO();
        System.out.println("===> JDBC로 getBoard() 기능 처리");
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_GET);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()) {
                one.setId(rs.getInt("id"));
                one.setPhoto(rs.getString("photo"));
                one.setFirstName(rs.getString("firstName"));
                one.setLastName(rs.getString("lastName"));
                one.setBirthday(rs.getString("birthday"));
                one.setGender(rs.getString("gender"));
                one.setEmail(rs.getString("email"));
                one.setPhone(rs.getString("phone"));
                one.setInformation(rs.getString("information"));
                one.setUserType(rs.getString("userType"));
                one.setAccount(rs.getString("account"));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return one;
    }

    public List<BoardVO> getBoardList(){
        List<BoardVO> list = new ArrayList<BoardVO>();
        System.out.println("===> JDBC로 getBoardList() 기능 처리");
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_LIST);
            rs = stmt.executeQuery();
            while(rs.next()) {
                BoardVO one = new BoardVO();
                one.setId(rs.getInt("id"));
                one.setPhoto(rs.getString("photo"));
                one.setFirstName(rs.getString("firstName"));
                one.setLastName(rs.getString("lastName"));
                one.setBirthday(rs.getString("birthday"));
                one.setGender(rs.getString("gender"));
                one.setEmail(rs.getString("email"));
                one.setPhone(rs.getString("phone"));
                one.setInformation(rs.getString("information"));
                one.setUserType(rs.getString("userType"));
                one.setAccount(rs.getString("account"));
                one.setRegdate(rs.getDate("regdate"));
                list.add(one);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getPhotoFilename(int id){
        String filename = null;
        try{
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_SELECT);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()){
                filename = rs.getString("photo");
            }
            rs.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("==> JDBC로 getPhotoFilename()기능 처리");
        return filename;
    }
}