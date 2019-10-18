package org.lanqiao.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.UUID;

public class FileUpload1 {
    public String upload(HttpServletRequest request, MultipartFile file) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String fileName = file.getOriginalFilename();
        //获取服务器所在地址
        String pathval = request.getSession().getServletContext().getRealPath("/");
        //图片在服务器下的路径
        String saveFilePath = "images/uploadFile";
        //寻找或者建立路径
        File fileDir = new File("D:/test/test-images/");
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        fileName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
        try {
            inputStream = file.getInputStream();
            outputStream = new FileOutputStream(fileDir + File.separator + fileName);
            byte[] b = new byte[1024];
            while ((inputStream.read(b)) != -1) {
                outputStream.write(b);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "D:/test/test-images/" + fileName;
    }
}

