package com.xxg.websocket;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghengqiang on 2017/10/27.
 */
@Slf4j
public class LLDirectory extends HttpServlet {

    private String absolutePath;

    @Override
    public void init() throws ServletException {
        absolutePath = this.getInitParameter("absolutePath");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        PrintWriter writer = null;
        try {
            File directory = new File(absolutePath);
            response.reset();
            response.setContentType("application/json;charset=utf-8;");
            writer = response.getWriter();
            File[] files = directory.listFiles();
            List<FileInfo> datas = new ArrayList<>(files.length);
            for (File file : files){
                if (file.isFile()){
                    FileInfo info = new FileInfo();
                    info.setName(file.getName());
                    info.setPath(file.getAbsolutePath().
                            replaceAll("/","|").
                            replaceAll("\\\\","|")
                    );
                    datas.add(info);
                }
            }

            writer.write(JSON.toJSONString(datas));
        }catch (Exception e ){
            e.printStackTrace();
        }finally {
            if (writer != null){
                writer.close();
            }
        }

    }
}
