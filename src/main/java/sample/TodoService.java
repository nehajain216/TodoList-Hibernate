/**
 * 
 */
package sample;

import java.util.List;

/**
 * @author Siva
 *
 */
public class TodoService {

	private TodoDAO todoDAO = new TodoDAO();
	
	public User login(String email, String password)
	{
		return todoDAO.login(email, password);
	}
	
	public List<Todo> getTodoList(int userId)
	{
		return todoDAO.getTodoList(userId);
	}
	
	public Todo getTodo(int id)
	{
		return todoDAO.getTodo(id);
	}
	
	public Todo createTodo(Todo todo)
	{
		return todoDAO.createTodo(todo);
	}
	
	public Todo updateTodo(Todo todo)
	{
		return todoDAO.updateTodo(todo);
	}
	
	public void deleteTodo(int todoId)
	{
		todoDAO.deleteTodo(todoId);
	}
	
	
}
