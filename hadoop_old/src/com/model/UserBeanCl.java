package com.model;  
  
import java.sql.Connection;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;  
   
  
public class UserBeanCl {  
    private Statement sm = null;  
    private Connection ct = null;  
    private ResultSet rs = null; 
    int m=0;
      
      
    public void close(){  
        try {  
              
              
        if(sm != null){   
            sm.close();  
            sm = null;  
        }  
          
        if(ct != null){  
            ct.close();  
            ct = null;  
        }  
          
          
        if(rs != null){  
            rs.close();  
            rs = null;  
        }  
          
          
        }  
        catch (SQLException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
      
      
      
    //检查登录用户是否合法  
        public boolean checkUser(String user, String password){  
            boolean b = false;        
            try {  
                  
                //获得连接  
                ct = new ConnDB().getConn();  
                //创建statement  
                sm = ct.createStatement();  
                  
                rs = sm.executeQuery("select * from user where username=\""+user+"\"");  
                  
                  
                if(rs.next()){  
                    //说明用户存在  
                    String pwd = rs.getString(1);  
                    if(password.equals(pwd)){  
                        //说明密码正确  
                        b = true;  
                    }else{  
                        b = false;  
                    }  
                      
                }else{  
                    b = false;  
                }     
            } catch (SQLException e) {  
                e.printStackTrace();  
            }finally{  
                this.close();  
            }  
              
            return b;  
        }  
        public boolean addUser(String user, String password){
        	boolean b = true;        
            try {
            	//获得连接  
                ct = new ConnDB().getConn();
                sm = ct.createStatement();
                m = sm.executeUpdate("insert into user values('"+user+"','"+password+"')");
                System.out.println("m:"+m+"");
            } catch (SQLException e) {  
                e.printStackTrace();  
            }finally{  
                this.close();  
            }
            if (m == 0)
            {
            	b=false;
            }

            return b;
        }
  
}