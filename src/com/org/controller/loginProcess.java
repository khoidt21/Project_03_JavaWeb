package com.org.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.org.model.UsersData;
import com.org.entity.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class loginProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginProcess() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet loginProcess</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet loginProcess at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}
	
	public void init() {
		UsersData.getInstance().loadDataByFileToHashMap();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(username, password);
        if(UsersData.getInstance().checkUser(user)) {
            //Login success
            UsersData.getInstance().addUsersOnline(user.getUsername());
            getServletContext().setAttribute("users", UsersData.getInstance());
            request.getSession().setAttribute("userNow", user);
            request.getSession().setAttribute("isNew", true);
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            // Check user information and check user in hashmap
            request.setAttribute("user", user);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        
	}
	
    @Override
    public String getServletInfo() {
        return "Servlet info description";
    }
    
}
