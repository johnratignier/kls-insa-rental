

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kls.configuration.DAOFactory;
import com.kls.dao.ClientDAO;
import com.kls.dao.DroitsMembreDAO;
import com.kls.dao.MembreDAO;
import com.kls.dao.StatutDAO;
import com.kls.javabean.Client;
import com.kls.javabean.DroitsMembre;
import com.kls.javabean.Materiel;
import com.kls.javabean.Membre;
import com.kls.javabean.Statut;
import com.kls.manage.ClientManage;
import com.kls.manage.MembreManage;

public class ListeMembreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MembreDAO membreDAO;
	private DroitsMembreDAO droitDAO;
	private StatutDAO statutDAO;


	public ListeMembreServlet() {
		super();
	}
	
	public void init(){
		DAOFactory f = (DAOFactory) (getServletContext().getAttribute("daoFactory"));
		this.membreDAO = (MembreDAO) f.getMembreDAO();
		this.droitDAO = (DroitsMembreDAO) f.getDroitsMembreDAO();
		this.statutDAO = (StatutDAO) f.getStatutDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id")==null && request.getParameter("modifiermembre")==null){
			HashMap<Integer, Membre> membreList = this.membreDAO.toList();
			request.getSession().setAttribute("membreListe", membreList);
			this.getServletContext().getRequestDispatcher("/WEB-INF/membrePage/membreListe.jsp").forward(request, response);
		}else if(request.getParameter("id")!=null){
			Membre membreCurrent = this.membreDAO.find(Integer.valueOf(request.getParameter("id")));
			request.setAttribute("membreCurrent",membreCurrent);
			this.getServletContext().getRequestDispatcher("/WEB-INF/membrePage/profileMembre.jsp").forward(request, response);
		}else if(request.getParameter("modifiermembre")!=null && request.getSession().getAttribute("membre")!=null){
			Membre membre = this.membreDAO.find(Integer.valueOf(request.getParameter("modifiermembre")));
			ArrayList<DroitsMembre> droitsmembre = this.droitDAO.toList();
			ArrayList<Statut> statutmembre = this.statutDAO.toList();
			request.setAttribute("membremodif", membre);
			request.setAttribute("droitsmembre", droitsmembre);
			request.setAttribute("statutmembre", statutmembre);
			this.getServletContext().getRequestDispatcher("/WEB-INF/membrePage/editionMembre.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MembreManage membreManage = new MembreManage(this.membreDAO);
		membreManage.updateMembre(request);
		System.out.println("MODIFICATION MEMBRE");
		response.sendRedirect("/kls/listemembres");
	}

}
