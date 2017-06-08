package sample;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;

/**
 * Servlet implementation class UpdateToDoServlet
 */
public class UpdateToDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateToDoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    	      throws ServletException, IOException
   {
    			//doPost(request,response);
    	String page = "UpdateToDo.jsp";
    	//HttpSession session = request.getSession();
		//User loginUser = (User) session.getAttribute("USER_LOGIN");	
		//int userId = loginUser.getId();
		String id = request.getParameter("id");	
		
		TodoService todoService = new TodoService();		
		Todo todo = todoService.getTodo(Integer.valueOf(id));
		
		request.setAttribute("EditTodoObject", todo);
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);		       
	    dispatcher.forward(request, response); 
		
   }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		String id = request.getParameter("id");
		String title = request.getParameter("title");		
		String description = request.getParameter("description");		
		Todo todo = new Todo();
		todo.setId(Integer.valueOf(id));		
		todo.setTitle(title);
		todo.setDescription(description);
		
		TodoService todoService = new TodoService();
		Todo tempTodo = todoService.updateTodo(todo);
		
		response.sendRedirect("MainPageServlet");			
	}

}
