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

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class AuthenticateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public AuthenticateServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.connexion(request, response);
		//this.getServletContext().getRequestDispatcher("/home").forward(request, response);
	}
	
	public void connexion(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String mailUser = request.getParameter("user");
		String res = null;
		try {
			System.out.print("-->>ENCORE ENCORE ??");
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdd_kls?verifyServerCertificate=false&useSSL=true", "root", "Insane0707");
			java.sql.Statement st = connexion.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM membre WHERE adresse_mail = \""+mailUser+"\"");
			rs.next();
			res = rs.getString("prenom");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("userName", res);
		System.out.print("-->>"+session.getAttribute("membre"));
		try {
			this.getServletContext().getRequestDispatcher("/home").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

}