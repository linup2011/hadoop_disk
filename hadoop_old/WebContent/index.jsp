<%@ include file="head.jsp"%>  
 <%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>   
<%@page import="org.apache.hadoop.fs.FileStatus"%>    

<script type="text/javascript">
function diag()
{
    var str=prompt("输入文件夹名称");
    if(str)
    {
        alert("您刚输入的是："+ str)
    }
    document.submitForm.dirname.value=str;
    document.submitForm.submit();
    //HttpRequest.setParameter("dirname",str);
}

</script>  
      
<body class="body">  
    <div class="header" >  
        <div class="navbar-inner">  
            <div class="logo" style="float:left"><a class="brand" href="/hadoop_old/IndexServlet" >网盘</a></div>  
            <ul class="nav">  
                <li><a href="LogoutServlet">退出</a></li>
                <li class="active"><a href="javascript:;"><%= request.getAttribute("username") %></a></li>
                   
            </ul>  
        </div>  
    </div>
    <div class="content">    
    <div style="margin:0px auto; text-align:left;width:1200px; height:50px;">  
       <form name="submitForm" class="form-inline"  method="POST"   action="NewdirServlet" >
           <div style="line-height:50px;float:left;">
              <input type="submit" name="submit" value="新建文件夹" onclick="diag()"  />
              <input type="hidden" name="dirname" value="tmp"/>
           </div>
       </form>
       <form class="form-inline"  method="POST"  enctype="MULTIPART/FORM-DATA"   action="UploadServlet" >  
           <div style="line-height:50px;float:left;">  
              <input type="submit" name="submit" value="上传文件"  />  
           </div>    
           <div style="line-height:50px;float:left;">  
              <input type="file" name="file1" size="30"/>  
           </div>    
       </form>  
   		<div style="float:right;"><a href="<%= request.getAttribute("lastpage") %>">返回</a></div>
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
              
            FileStatus[] list = (FileStatus[])request.getAttribute("list");  
            if(list != null)  
            for (int i=0; i<list.length; i++) {  
          %>  
                    <tr  style=" border-bottom:2px solid #ddd">  
                    <%  
                        if(list[i].isDir())  
                        {  
                            out.print("<td><a href=\"IndexServlet?filepath="+list[i].getPath()+"\">"+list[i].getPath().getName()+"</a></td>");  
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