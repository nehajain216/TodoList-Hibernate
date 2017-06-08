package sample;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }
	private TodoService todoService = new TodoService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String email = request.getParameter("email");	
		String password = request.getParameter("pwd");	
		
		User user= todoService.login(email, password);
		
		if(user !=null)
		{			
			HttpSession session = request.getSession(true);
			session.setAttribute("USER_LOGIN", user);
			response.sendRedirect("MainPageServlet");			
		}
		else
		{			
			response.sendRedirect("LoginPage.jsp");			
		}
	}

}
