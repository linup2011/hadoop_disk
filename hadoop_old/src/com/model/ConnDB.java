package com.model;  
  
import java.sql.Connection;  
import java.sql.DriverManager;  
  
public class ConnDB {  
    private Connection ct = null;  
    public Connection getConn(){  
          
    try {  
    //加载驱动  
    Class.forName("com.mysql.jdbc.Driver");  
              
    //得到连接  
    ct = DriverManager.getConnection("jdbc:mysql://localhost:3306/hadoop?user=root&password=");  
    } catch (Exception e) {  
    // TODO Auto-generated catch block  
    e.printStackTrace();  
    }  
          
          
    return ct;  
    }  
}