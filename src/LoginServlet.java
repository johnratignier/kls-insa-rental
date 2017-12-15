

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kls.configuration.DAOFactory;

import com.kls.dao.MembreDAO;
import com.kls.manage.MembreManage;


public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private MembreDAO membreDAO;

	public LoginServlet() {
		super();
	}

	public void init(){
		DAOFactory f = (DAOFactory) (getServletContext().getAttribute("daoFactory"));
		this.membreDAO = (MembreDAO) f.getMembreDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("membre")!=null){
			this.getServletContext().getRequestDispatcher("/home").forward(request, response);
		}else{
			this.getServletContext().getRequestDispatcher("/WEB-INF/loginPage/login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MembreManage membreManage = new MembreManage(this.membreDAO);
		boolean b = membreManage.verifierLogin(request.getParameter("mail"), request.getParameter("password"), request, response);
		if(b){
			this.getServletContext().getRequestDispatcher("/home").forward(request, response);
		}else{
			this.getServletContext().getRequestDispatcher("/WEB-INF/loginPage/login.jsp").forward(request, response);
		}
	}

}
