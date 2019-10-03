package com.org.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.org.model.UsersData;
import com.org.entity.User;

/**
 * Servlet implementation class SignupController
 */
@WebServlet("/SignupController")
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupController() {
        super();
        // TODO Auto-generated constructor stub
    }

	//  Database db;
	  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	          throws ServletException, IOException {
	
	      response.setContentType("text/html;charset=UTF-8");
	      response.sendRedirect(request.getContextPath() + "/sign-up.jsp");
	  }
	
	  public void init() throws ServletException {
	      UsersData.getInstance().loadDataByFileToHashMap();
	  }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = new User(request.getParameter("username"), request.getParameter("password"));

		// check validation user and check username exists in HashMap
        if (user.validationUser() && !UsersData.getInstance().hasUser(user.getUsername())) {
            //success add user to database
            UsersData.getInstance().addUser(user.getUsername(), user.getPassword());

            //save data to file txt
            UsersData.getInstance().saveUsers();
             
            //goto login.jsp
            request.getServletContext().setAttribute("users", UsersData.getInstance());
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else { 
            if (UsersData.getInstance().hasUser(user.getUsername())) {
                user.addError("Username does not exist.");
            }
            request.setAttribute("user", user);
            request.getRequestDispatcher("/sign-up.jsp").forward(request, response);
        }
	}
	
    @Override
    public String getServletInfo() {
        return "Servlet info description";
    }// </editor-fold>
    
    public void destroy() {
        super.destroy();
        UsersData.getInstance().saveUsers();
    }
}
