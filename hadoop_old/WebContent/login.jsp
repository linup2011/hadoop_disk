<%@ include file="head.jsp"%>
 <%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>   
      
<body style="text-align:center;margin-bottom:100px;">  
         <div class="navbar" >  
         <div class="navbar-inner">  
           <a class="brand" href="#" style="margin-left:200px;">网盘</a>  
         
         </div>  
       </div>  
        <div  style="text-align:left;margin:0px auto;margin-top:50px; width:1200px;height:500px;">  
            <div style="float:left;width:800px; height:100%;background:#87CEEB"></div>  
            <div style="float:left;width:400px; height:100%; background:#87CEEB">  
           
                <form  action="LoginServlet" method="post" class="form-horizontal" style="margin-top:150px;margin-left:100px;">  
               
                         用户  <input type="text" id="inputEmail" name="username" >  
                    <br><br>  
                        密码  <input type="password" id="inputPassword"  name="password">  
                    <br><br>  
                    <div style="float:left; width:120px;"> 
                   		<button type="submit" class="btn" style="background:">登陆</button>  
                   	</div>
                    <div  style="float:left; width:120px; margin-left:50px"> <a href="register.jsp" style='display:boock; width:66px; height:38px; line-height:38px; text-align:right' >用户注册</a> </div>      
               </form>
            </div>
            
            </fieldset>  
            </div>  
        
        </div>  
           
</body>