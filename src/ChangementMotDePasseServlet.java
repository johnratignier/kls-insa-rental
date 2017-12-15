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
import com.kls.dao.DroitsMembreDAO;
import com.kls.dao.MembreDAO;
import com.kls.dao.StatutDAO;
import com.kls.javabean.Membre;
import com.kls.manage.BCrypt;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class ChangementMotDePasseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MembreDAO membreDAO;

	public ChangementMotDePasseServlet() {
		super();
	}
	
	public void init(){
		DAOFactory f = (DAOFactory) (getServletContext().getAttribute("daoFactory"));
		this.membreDAO = (MembreDAO) f.getMembreDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/membrePage/motdepasse.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Membre mb = (Membre) request.getSession().getAttribute("membre");
		Integer id = mb.getId_user();
		if(request.getParameter("mdpa").equals(request.getParameter("mdpb"))){
			this.membreDAO.updateMDP(id, BCrypt.hashpw(request.getParameter("mdpa"), BCrypt.gensalt()));
			response.sendRedirect("/kls/home");
		}else{
			response.sendRedirect("/kls/changementmotdepasse");
		}
	}

}