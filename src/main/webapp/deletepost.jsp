<%--
  Created by IntelliJ IDEA.
  User: haeun
  Date: 2022/11/28
  Time: 1:51 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.example.dao.BoardDAO, com.example.bean.BoardVO"%>
<%@ page import="com.example.file.FileUpload" %>
<%
    String sid = request.getParameter("id");
    if (sid != ""){
        int id = Integer.parseInt(sid);
        BoardDAO boardDAO = new BoardDAO();
        BoardVO u = new BoardVO();

        //업로드한 이미지 파일 삭제
        String filename = boardDAO.getPhotoFilename(id);
        if(filename != null){
            FileUpload.deleteFile(request, filename);
        }
        u.setId(id);
        boardDAO.deleteBoard(u);
    }
    response.sendRedirect("post.jsp");
%>
