import java.io.*;
import java.sql.*;
import java.util.*;
public class text { 
public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{ 
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/whatsKonsole", "root", "123456");
      stmt = conn.createStatement();

      File file = new File("/home/anchal/Downloads/cclub_public.txt");

        // if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

      String sql = "SELECT DISTINCT userId FROM users WHERE userId NOT IN (SELECT DISTINCT userId FROM users WHERE groupName='cclub public announcement')";
      //String sql = "select DISTINCT userId, groupName from users where userId IN (select distinct userId from users) order by userId";
      //String sql = "SELECT DISTINCT userId from users";
        ResultSet rs = stmt.executeQuery(sql);
	

      while(rs.next()){
         
         String contact = rs.getString("userId");
	 bw.append(contact);
         bw.append("\n");
         
       }
      
      rs.close();
      //rs2.close();
      bw.close();
   }
catch(SQLException se){ 
      se.printStackTrace(); 
   }
catch(Exception e){
      e.printStackTrace();
   }
finally{ 
      try{ 
         if(stmt!=null)
            conn.close();
      }
      catch(SQLException se){ 
      } 
      try{ 
         if(conn!=null)
            conn.close();
      }
      catch(SQLException se){ 
         se.printStackTrace(); 
      } 
   } 
}
}
