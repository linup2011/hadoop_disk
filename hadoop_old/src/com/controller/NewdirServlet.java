package com.controller;  
  
import java.io.IOException;  
  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
  
import org.apache.hadoop.fs.FileStatus;  
import org.apache.hadoop.mapred.JobConf;  
  
  
  
import com.model.*;  
  
/** 
 * Servlet implementation class ListServlet 
 */  
public class NewdirServlet extends HttpServlet {  
   
  
    /** 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) 
     */  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        this.doPost(request, response);  
    }  
  
    /** 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) 
     */  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
      
        String username = (String) request.getSession().getAttribute("username");  
        String dirname = request.getParameter("dirname");   
  
        String dirpath = (String) request.getSession().getAttribute("dirpath"); 
        System.out.println("dirname:"+dirname+"");
        System.out.println("dirpath:"+dirpath+"");
        JobConf conf = HdfsDAO.config(); 
        HdfsDAO hdfs = new HdfsDAO(conf);  
        hdfs.mkdirs(dirpath + "/" + dirname);
        response.sendRedirect( "/hadoop_old/IndexServlet");
        //FileStatus[] list = hdfs.ls(dirpath);  
        //request.setAttribute("list",list);  
        //request.getRequestDispatcher("index.jsp").forward(request, response);  
         
       
    }  
  
}