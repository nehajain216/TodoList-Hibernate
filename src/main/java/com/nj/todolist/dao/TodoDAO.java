/**
 * 
 */
package com.nj.todolist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.nj.todolist.entities.Todo;
import com.nj.todolist.entities.User;
import com.nj.todolist.utils.DBUtil;
import com.nj.todolist.utils.PersistenceManager;

/**
 * @author Siva
 *
 */
public class TodoDAO {

	public User login(String email, String password) {
		ResultSet rs = null;
		User user = null;
		try {
			String sql = "select * from userdetails where email=? and password=?";
			Connection connection = DBUtil.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public List<Todo> getTodoList(int userId) 
	{
		List<Todo> toDoListData = new ArrayList<Todo>();
		try
		{			
			EntityManager em = PersistenceManager.getEntityManager();
			//em.getTransaction().begin();
			//toDoListData = em.find(Todo.class, userId).getResultList();
			
			toDoListData = em.createQuery("select * from todolist t where t.userid = :userid",
				    Todo.class).setParameter("userid", userId).getResultList();
			em.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return toDoListData;
				
		
		/*ResultSet rs = null;
		Connection connection = null;
		List<Todo> toDoListData = new ArrayList<Todo>();
		try {
			String sql = "Select * from todolist where userid =?";
			connection = DBUtil.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Todo todo = new Todo();
				int id = rs.getInt("id");
				int userid = rs.getInt("userid");
				String title = rs.getString("title");
				String description = rs.getString("description");

				todo.setId(id);
				todo.setUserId(userid);
				todo.setTitle(title);
				todo.setDescription(description);
				toDoListData.add(todo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return toDoListData;*/
	}

	public Todo getTodo(int todoId) 
	{
		Todo todoRecord = new Todo();
		try {
			EntityManager em = PersistenceManager.getEntityManager();
			// em.getTransaction().begin();
			todoRecord = em.find(Todo.class, todoId);
			em.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return todoRecord;
	}
	
	public Todo createTodo(Todo todo) {

		try {
			EntityManager em = PersistenceManager.getEntityManager();
			em.getTransaction().begin();
			em.persist(todo);
			em.getTransaction().commit();
			em.close();

		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return todo;
	}

	public Todo updateTodo(Todo todo) {
		try {
			EntityManager em = PersistenceManager.getEntityManager();
			em.getTransaction().begin();
			Todo savedTodo = em.find(Todo.class, todo.getId());
			savedTodo.setDescription(todo.getDescription());
			savedTodo.setTitle(todo.getTitle());
			em.merge(savedTodo);

			em.getTransaction().commit();
			em.close();

		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return todo;
	}

	public void deleteTodo(int todoId) {
		try {
			EntityManager em = PersistenceManager.getEntityManager();
			em.getTransaction().begin();
			Todo deleteTodo = em.find(Todo.class, todoId);
			em.remove(deleteTodo);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	}
}
