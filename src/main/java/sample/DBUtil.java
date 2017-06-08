package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class DBUtil 
{
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		String driverClass = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test?useSSL=true";
        String username = "root";
        String password = "admin";
        Class.forName(driverClass);
        Connection connection = DriverManager.getConnection(url,username,password);
        return connection;
	}
		
	public static int loggedInUserId(String loginuseremail)
	{
		ResultSet rs = null;
		int userId = 0;
		try
		{
			String sql = "select id from userdetails where email=?";
			Connection connection = DBUtil.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, loginuseremail);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				userId = rs.getInt("id");
			}						
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return userId;
	}
	
	
	
	
}
