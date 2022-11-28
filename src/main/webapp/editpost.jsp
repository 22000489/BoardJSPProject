<%--
  Created by IntelliJ IDEA.
  User: haeun
  Date: 2022/11/28
  Time: 3:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.example.dao.BoardDAO"%>
<%@ page import="com.example.file.FileUpload" %>
<%@ page import="com.example.bean.BoardVO" %>

<% request.setCharacterEncoding("utf-8");

    BoardDAO boardDAO = new BoardDAO();
    FileUpload upload = new FileUpload();
    BoardVO u = upload.uploadPhoto(request);

    int i=boardDAO.updateBoard(u);
    response.sendRedirect("post.jsp");
%>
