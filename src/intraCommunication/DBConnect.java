/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraCommunication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alger
 */
/*public class DBConnect {
    Connection conn = null;
    public static Connection DBConnect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/","root","a1b2c9d8e2");
            return conn;
        }catch(Exception ex){
            System.out.println(ex);
            return null;
        }

    }
}
*/
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {
    private static Connection conn;
    private static Statement st;
    private static ResultSet  result;
    public  static Connection getConnection()
    {
        try
        {
        String url="jdbc:mysql://127.0.0.1/perlinchat";
        String userName="root";
        String password="a1b2c9d8e2";
        Class.forName("com.mysql.jdbc.Driver");
        conn=DriverManager.getConnection(url, userName, password);
        }
        catch(SQLException ex)
        {
            System.out.println("error"+ex.getMessage());
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("error"+ex.getMessage());
        }
        finally
        {
            return conn;
        }
    }

    public ResultSet searchQuery(String sql)
    {
        try
        {
            getConnection();
            st=conn.createStatement();
            result = st.executeQuery(sql);

        }
        catch(SQLException ex)
        {
            System.out.println("Error"+ex.getMessage());
        }

        return result;
}
}
