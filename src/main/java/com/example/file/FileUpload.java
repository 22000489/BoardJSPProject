package com.example.file;

import com.example.bean.BoardVO;
import com.example.dao.BoardDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class FileUpload {
    public BoardVO uploadPhoto(HttpServletRequest request){
        String filename = "";
        int sizeLimit = 15 * 1024 * 1024; // 한번에 올릴 수 있는 파일 용량 : 15M로 제한

        String realPath = request.getServletContext().getRealPath("upload");
        ////System.out.println(realPath);

        //upload 폴더가 없는 경우 폴더를 만들어라
        File dir = new File(realPath);
        if (!dir.exists()) dir.mkdirs();

        BoardVO one = null;
        MultipartRequest multipartRequest = null;
        try{
            multipartRequest = new MultipartRequest(request, realPath, sizeLimit, "utf-8",new DefaultFileRenamePolicy());
            filename = multipartRequest.getFilesystemName("photo");

            one = new BoardVO();
            String sid = multipartRequest.getParameter("id");
            if(sid!=null&&!sid.equals("")) one.setId(Integer.parseInt(sid));
            one.setFirstName(multipartRequest.getParameter("firstName"));
            one.setLastName(multipartRequest.getParameter("lastName"));
            one.setBirthday(multipartRequest.getParameter("birthday"));
            one.setGender(multipartRequest.getParameter("gender"));
            one.setEmail(multipartRequest.getParameter("email"));
            one.setPhone(multipartRequest.getParameter("phone"));
            one.setInformation(multipartRequest.getParameter("information"));
            one.setUserType(multipartRequest.getParameter("userType"));
            one.setAccount(multipartRequest.getParameter("account"));

            if (sid!=null && !sid.equals("")){  //edit인 경우 기존 파일이름과 비교
                BoardDAO dao = new BoardDAO();
                String oldfilename = dao.getPhotoFilename(Integer.parseInt(sid));
                if(filename!=null && oldfilename!=null) //새로 덮어씌울 파일이 있으면 이전 파일 제거
                    FileUpload.deleteFile(request, oldfilename);
                else if(filename==null && oldfilename !=null) //새로운 파일 없는 경우 이전 파일 유지
                    filename = oldfilename;

            }

            one.setPhoto(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return one;
    }

    public static void deleteFile(HttpServletRequest request, String filename){
        String filePath = request.getServletContext().getRealPath("upload");

        File f = new File(filePath + "/" + filename);
        if(f.exists()) f.delete();
    }



}