import java.sql.*;

public class connection{
    static String url="jdbc:postgresql://localhost:5432/nobank";
    static String user="postgres";
    static String password="root123";
    public static Connection getcon(){

        try{
            Class.forName("org.postgresql.Driver");
            Connection con= DriverManager.getConnection(url,user,password);
            System.out.println("Connected Successfully");
             return con;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    
    }
}