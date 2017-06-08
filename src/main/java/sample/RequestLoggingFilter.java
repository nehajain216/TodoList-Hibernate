package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.fabric.xmlrpc.base.Array;

/**
 * Servlet Filter implementation class RequestLoggingFilter
 */
public class RequestLoggingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public RequestLoggingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		HttpSession session = ((HttpServletRequest) request).getSession(true);
		String requestURI = ((HttpServletRequest) request).getRequestURI();
		Boolean defaultPage = false;
		List<String> defaultURI = Arrays.asList("/ToDoList/LoginPage.jsp","/ToDoList/LoginServlet");
		
		for(String uri: defaultURI)
		{
			if(uri.equals(requestURI))
			{
				defaultPage = true;
				break;
			}
		}
			if(defaultPage)
			{
				chain.doFilter(request, response);
			}
			else
			{				
				Object loginUser = session.getAttribute("USER_LOGIN");				
				if(loginUser != null)
					chain.doFilter(request, response);
				else
					((HttpServletResponse) response).sendRedirect("LoginPage.jsp");				
			}			
		}
	

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
