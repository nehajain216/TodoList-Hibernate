package com.nj.todolist.web.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nj.todolist.entities.Todo;
import com.nj.todolist.entities.User;
import com.nj.todolist.services.TodoService;
import com.nj.todolist.utils.PersistenceManager;

/**
 * Servlet implementation class NewToDoListServlet
 */
public class NewToDoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewToDoListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("USER_LOGIN");

		Todo todo = new Todo();
		int userId = loginUser.getId();
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		todo.setUserId(userId);
		todo.setTitle(title);
		todo.setDescription(description);

		TodoService todoService = new TodoService();
		Todo temptodo = todoService.createTodo(todo);
		response.sendRedirect("MainPageServlet");
	}

}
