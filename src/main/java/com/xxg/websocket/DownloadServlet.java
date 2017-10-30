package com.xxg.websocket;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.zeroturnaround.zip.FileSource;
import org.zeroturnaround.zip.ZipEntrySource;
import org.zeroturnaround.zip.ZipUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;

/**
 * Created by zhanghengqiang on 2017/10/27.
 */
@Slf4j
public class DownloadServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response){
        OutputStream writer = null;
        final String file = request.getParameter("file")
                .replaceAll("%7C","|").replaceAll("\\|","/");
        final String zipName = file.substring(file.lastIndexOf("/") + 1) + ".zip";
        log.info(file);
        try {
            response.reset();
            response.setContentType("application/octet-stream");
            response.setHeader("content-disposition", "attachment;filename=" + zipName);
            writer = response.getOutputStream();

            ZipEntrySource source = new FileSource(file,new File(file));

            ZipUtil.pack(new ZipEntrySource[]{source},writer);

        }catch (Exception e ){
            e.printStackTrace();
        }finally {
            if (writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
