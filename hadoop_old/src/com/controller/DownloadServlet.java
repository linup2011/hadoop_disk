package com.controller;  
  
import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;  
import javax.servlet.ServletOutputStream;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
import org.apache.hadoop.fs.FileStatus;  
import org.apache.hadoop.mapred.JobConf;  
  
import com.model.HdfsDAO;  
  
/** 
 * Servlet implementation class DownloadServlet 
 */  
public class DownloadServlet extends HttpServlet {  
    private static final long serialVersionUID = 1L;  
  
    /** 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) 
     */  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {  
        String local = "/tmp/";
        
        String filePath = new String(request.getParameter("filePath").getBytes("ISO-8859-1"),"GB2312");  
        System.out.println("remote:"+filePath);  
        System.out.println("local:"+local);
        JobConf conf = HdfsDAO.config();  
        HdfsDAO hdfs = new HdfsDAO(conf);  
        hdfs.download(filePath, local);
        
        String downfilename = filePath.substring( filePath.lastIndexOf("/")+1);
        System.out.println("downfilename:"+downfilename);
        File downfile = new File("/tmp/" + downfilename);
      //设置响应头，控制浏览器下载该文件
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(downfilename, "UTF-8"));
      //读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream("/tmp/"  + downfilename);
      //创建输出流
        OutputStream out = response.getOutputStream();
        byte buffer[] = new byte[1024];
        int len = 0;
      //循环将输入流中的内容读取到缓冲区当中
        while((len=in.read(buffer))>0){
        	//输出缓冲区的内容到浏览器，实现文件下载
        	out.write(buffer, 0, len);
        }
        in.close();
        out.close();
          
//        String username = (String) request.getSession().getAttribute("username");
//        FileStatus[] list = hdfs.ls("/"+username);  
//        request.setAttribute("list",list);  
//        request.getRequestDispatcher("index.jsp").forward(request,response);   
    }  
  
    /** 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) 
     */  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        this.doGet(request, response);  
    }  
  
}