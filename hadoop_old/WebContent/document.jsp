<%@ include file="head.jsp"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>   
<%@page import="org.apache.hadoop.fs.FileStatus"%>    
   
<body class="body">  
    <div class="header" >  
        <div class="navbar-inner">  
            <div class="logo"><a class="brand" href="javascript:;" >网盘</a></div>  
            <ul class="nav">  
                <li class="active"><a href="/">首页</a></li>   
            </ul>  
        </div>  
    </div>  
         
     
         
    <div class="content">  
        <div class="navbox">  
           <form class="form-inline"  method="POST"  enctype="MULTIPART/FORM-DATA"   action="UploadServlet" >  
               <div style="float:left; margin-right:20px">  
                  <input type="submit" name="submit" value="上传文件" class="btn" />  
               </div>    
               <div style="float:left;">  
                  <input type="file" name="file1" size="30" class="btn"/>   
               </div>    
           </form>  
       
        </div> 

        <div class="contList">
        <table  class="table table-hover" >  
            <tr class="title">
              <td width="60%" >文件名</td>
              <td width="10%">属性</td>
              <td width="10%">大小(KB)</td>
              <td width="10%">操作</td>
              <td width="10%">操作</td>
            </tr>  
          <%  
              
            FileStatus[] list = (FileStatus[])request.getAttribute("documentList");  
            if(list != null)  
            for (int i=0; i<list.length; i++) {  
          %>  
                    <tr  style=" border-bottom:2px solid #ddd">  
                    <%  
                        if(list[i].isDir())  
                        {  
                            out.print("<td><a href=\"IndexServlet?filePath="+list[i].getPath()+"\">"+list[i].getPath().getName()+"</a></td>");  
                        }else{  
                            out.print("<td>"+list[i].getPath().getName()+"</td>");  
                        }  
                    %>  
                        <td><%= (list[i].isDir()?"目录":"文件") %></td>  
                        <td><%= list[i].getLen()/1024%></td>  
                        <td><a href="DeleteFileServlet?filePath=<%=java.net.URLEncoder.encode(list[i].getPath().toString(),"GB2312") %>">删除</a></td>  
                        <td><a href="DownloadServlet?filePath=<%=java.net.URLEncoder.encode(list[i].getPath().toString(),"GB2312") %>">下载</a></td>  
                    </tr>  
                  
          <%  
            }  
          %>  
        </table> 
        </div>  
    </div> 

    <div class="foot">
       <center> 声明：demo只供学习用，请勿用于商业开发</center>
    </div> 
</body>  
</html>