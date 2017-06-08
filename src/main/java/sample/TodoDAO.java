/**
 * 
 */
package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Siva
 *
 */
public class TodoDAO {
	
	public User login(String email, String password)
	{
		ResultSet rs = null;
		User user = null;			
		try 
		{
			String sql = "select * from userdetails where email=? and password=?";	
			Connection connection = DBUtil.getConnection();	
			PreparedStatement pstmt = connection.prepareStatement(sql);              
            pstmt.setString(1, email);
            pstmt.setString(2, password);            
            rs = pstmt.executeQuery();
			
			if(rs.next()){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return user;
	}
	
	public List<Todo> getTodoList(int userId)
	{
		ResultSet rs = null;	
		Connection connection = null;
		List<Todo> toDoListData = new ArrayList<Todo>();
		try
		{						
			String sql = "Select * from todolist where userid =?";
			connection = DBUtil.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);			
			pstmt.setInt(1, userId);
			rs=pstmt.executeQuery();					
			while(rs.next())
			{
				Todo todo = new Todo();
				int id = rs.getInt("id");
				int userid = rs.getInt("userid");
				String title = rs.getString("title");
				String description= rs.getString("description");
				
				todo.setId(id);
				todo.setUserId(userid);
				todo.setTitle(title);
				todo.setDescription(description);
				toDoListData.add(todo);
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		 finally
	        {
	        	try {					
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}            
	        }
		return toDoListData;
	}
	
	public Todo getTodo(int todoId)
	{
		ResultSet rs = null;
		Connection connection = null;
		Todo todo = new Todo();
		try
		{
			String sql = "select * from todolist where id=?";
			connection = DBUtil.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, todoId);
			rs= pstmt.executeQuery();
			while(rs.next())
			{				
				int id = rs.getInt("id");
				int userId = rs.getInt("userid");
				String title = rs.getString("title");
				String description = rs.getString("description");
				
				todo.setId(id);
				todo.setUserId(userId);
				todo.setTitle(title);
				todo.setDescription(description);
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
        {
        	try {					
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}            
        }
		return todo;
	}
	
	public Todo createTodo(Todo todo)
	{
		Connection connection = null;
		PreparedStatement pstmt = null;		
		try 
		{
			connection = DBUtil.getConnection();			
			String sql = "insert into todolist(userid, title,description) values(?,?,?)";
			
			int userId = todo.getUserId();
			String title = todo.getTitle();
			String description = todo.getDescription();
			
			pstmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, userId);
            pstmt.setString(2, title);
            pstmt.setString(3, description);
            
            pstmt.executeUpdate(); 
            
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();                       
            todo.setId(rs.getInt(1));
            pstmt.close();
			
		}  
		catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
         }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
         } 
        finally
        {
        	try {				
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}            
        }
		return todo;
	}
	
	public Todo updateTodo(Todo todo)
	{
		Connection connection = null;
		PreparedStatement pstmt = null;		
		try 
		{
			connection = DBUtil.getConnection();			
			String sql = "update todolist SET title=? , description=? where id=?";
			
			int id = todo.getId();
			String title = todo.getTitle();
			String description = todo.getDescription();			
			pstmt = connection.prepareStatement(sql);
            
            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.setInt(3, id);            
          
            pstmt.executeUpdate();            
            pstmt.close();
		}  
		catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
         }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
         } 
        finally
        {
        	try {				
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}            
        }
		return todo;
	}
	
	public void deleteTodo(int todoId)
	{
		Connection connection = null;	
		PreparedStatement pstmt = null;
		try
		{
			connection = DBUtil.getConnection();
			String sql= "delete from todolist where id=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, todoId);
			pstmt.executeUpdate();
			pstmt.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
        {
        	try {				
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}            
        }
		
	}
}
