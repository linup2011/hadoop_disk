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
public class IndexServlet extends HttpServlet {  
   
  
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
    	String filePathtmp = request.getParameter("filepath");
    	FileStatus[] list;
    	System.out.println("filePathtmp:"+filePathtmp+"");
        String username = (String) request.getSession().getAttribute("username");
    	if ( filePathtmp != null )
    	{
    		String filePath= new String(filePathtmp.getBytes("ISO-8859-1"),"GB2312");
    		HttpSession session = request.getSession(); 
            session.setAttribute("dirpath", filePath);
            JobConf conf = HdfsDAO.config();  
            HdfsDAO hdfs = new HdfsDAO(conf);
            System.out.println("indexservlet:filePath:"+filePath+"");
            list = hdfs.ls(filePath);
    	}
    	else
    	{
    	//String dirpath = (String) request.getSession().getAttribute("dirpath");
    	JobConf conf = HdfsDAO.config();  
        HdfsDAO hdfs = new HdfsDAO(conf);
        System.out.println("indexservlet:dirpath:"+username+"");
        	list = hdfs.ls("/"+username);
    	}
    	String url = request.getHeader("Referer");
        request.setAttribute("list",list);  
        request.setAttribute("lastpage",url );
        request.setAttribute("username",username );  
        request.getRequestDispatcher("index.jsp").forward(request, response);  
       
    }  
  
}