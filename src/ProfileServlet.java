

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kls.configuration.DAOFactory;
import com.kls.dao.DroitsMembreDAO;
import com.kls.dao.MembreDAO;
import com.kls.dao.StatutDAO;
import com.kls.javabean.DroitsMembre;
import com.kls.javabean.Statut;
import com.kls.javabean.TypeMateriel;
import com.kls.manage.MembreManage;



public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MembreDAO membreDAO;
	private DroitsMembreDAO droitsMembreDAO;
	private StatutDAO statutDAO;

	public ProfileServlet() {
		super();

	}

	public void init(){
		DAOFactory f = (DAOFactory) (getServletContext().getAttribute("daoFactory"));
		this.membreDAO = (MembreDAO) f.getMembreDAO();
		this.droitsMembreDAO = (DroitsMembreDAO) f.getDroitsMembreDAO();
		this.statutDAO = (StatutDAO) f.getStatutDAO();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print(request.getParameter("membreProfil"));
		if(request.getParameter("membreProfil").equals("me") && request.getSession().getAttribute("membre")!=null){
			ArrayList<DroitsMembre> listedroits = this.droitsMembreDAO.toList();
			ArrayList<Statut> listestatut = this.statutDAO.toList();
			request.setAttribute("droitsmembre", listedroits);
			request.setAttribute("statutmembre", listestatut);
			this.getServletContext().getRequestDispatcher("/WEB-INF/membrePage/profile.jsp").forward(request, response);
		}else{
			this.getServletContext().getRequestDispatcher("/home").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MembreManage membreManage = new MembreManage(this.membreDAO);
		membreManage.updateMembre(request);
		System.out.println("MODIFICATION PROFILE");
		response.sendRedirect("/kls/listemembres");
	}

}
