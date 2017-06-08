package sample;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("USER_LOGIN");	
		
		Todo todo = new Todo();		
		int  userId = loginUser.getId();
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		todo.setUserId(userId);
		todo.setTitle(title);
		todo.setDescription(description);
		
		TodoService todoService = new TodoService();		
		Todo temptodo = todoService.createTodo(todo);
		// int status = (loginUser.getEmail(), title, description);
		response.sendRedirect("MainPageServlet");			
	}

}
