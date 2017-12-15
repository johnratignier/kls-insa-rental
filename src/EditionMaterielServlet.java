

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.kls.configuration.DAOFactory;
import com.kls.dao.EtatMaterielDAO;
import com.kls.dao.MaterielDAO;
import com.kls.dao.MembreDAO;
import com.kls.dao.TypeMaterielDAO;
import com.kls.javabean.EtatMateriel;
import com.kls.javabean.Materiel;
import com.kls.javabean.TypeMateriel;
import com.kls.manage.MaterielManage;


public class EditionMaterielServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TypeMaterielDAO typeMaterielDAO;
	private EtatMaterielDAO etatMaterielDAO;
	private MaterielDAO materielDAO;


	public EditionMaterielServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(){
		DAOFactory f = (DAOFactory) (getServletContext().getAttribute("daoFactory"));
		this.typeMaterielDAO =  (TypeMaterielDAO) f.getTypeMaterielDAO();
		this.etatMaterielDAO = (EtatMaterielDAO) f.getEtatMaterielDAO();
		this.materielDAO = (MaterielDAO) f.getMaterielDAO();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id")!=null){
			ArrayList<EtatMateriel> listeEtatMateriel = this.etatMaterielDAO.toList();
			ArrayList<TypeMateriel> listeTypeMateriel = this.typeMaterielDAO.toList();
			request.setAttribute("opt_type_materiel", listeTypeMateriel);
			request.setAttribute("opt_etat_materiel", listeEtatMateriel);
			Materiel mat = this.materielDAO.find(request.getParameter("id"));
			request.setAttribute("materiel", mat);
			this.getServletContext().getRequestDispatcher("/WEB-INF/materielPage/editionMateriel.jsp").forward(request, response);
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MaterielManage materielManage = new MaterielManage(this.materielDAO);
		materielManage.updateMateriel(request);
		System.out.println("PROPOSITION DE MATERIEL :"+request.getHeader("accept-charset")+request.getHeader("accept-encoding"));
		response.sendRedirect("/kls/materiel");
	}

}
