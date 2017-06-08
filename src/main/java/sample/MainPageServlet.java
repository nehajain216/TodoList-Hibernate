package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MainPageServlet
 */
public class MainPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		List<Todo> toDoListData =new ArrayList<Todo>();		
		try
		{
			String page = "MainPage.jsp";
			HttpSession session = request.getSession();
			request.setAttribute("myname1", "siva");
			session.setAttribute("myname", "neha");			
			
			User loginUser = (User) session.getAttribute("USER_LOGIN");	
			int id = loginUser.getId();
			TodoService todoService = new TodoService();
			toDoListData = 	todoService.getTodoList(id);						
			request.setAttribute("toDoList",toDoListData);
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);		       
		    dispatcher.forward(request, response);        
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

}
