

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
import com.kls.javabean.TypeMateriel;
import com.kls.manage.MaterielManage;


public class NouveauMaterielServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TypeMaterielDAO typeMaterielDAO;
	private EtatMaterielDAO etatMaterielDAO;
	private MaterielDAO materielDAO;


	public NouveauMaterielServlet() {
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
		ArrayList<EtatMateriel> listeEtatMateriel = this.etatMaterielDAO.toList();
		ArrayList<TypeMateriel> listeTypeMateriel = this.typeMaterielDAO.toList();
		request.setAttribute("opt_type_materiel", listeTypeMateriel);
		request.setAttribute("opt_etat_materiel", listeEtatMateriel);
		this.getServletContext().getRequestDispatcher("/WEB-INF/materielPage/nouveauMateriel.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MaterielManage materielManage = new MaterielManage(this.materielDAO);
		Part part = request.getPart("photo_materiel");
		String nomFichier = null;
		System.out.print(part + request.getParameter("label"));
		for (String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
			/* Recherche de l'éventuelle présence du paramètre "filename". */
			if ( contentDisposition.trim().startsWith("filename") ) {
				/* Si "filename" est présent, alors renvoi de sa valeur, c'est-à-dire du nom de fichier. */
				nomFichier = contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 );
				nomFichier = nomFichier.substring(1, nomFichier.length()-1);
			}
		}
		if ( nomFichier != null && !nomFichier.isEmpty() ) {
			String cheminFichier = this.getServletContext().getRealPath("img/materielImage/");
			String c = "C:/Users/UTILISATEUR/Documents/Programmation JAVA EE/kls/WebContent/img/materielImage/";
			materielManage.uploadPhotoMateriel(request, part, nomFichier, cheminFichier);
		}
		
		materielManage.nouveauMateriel(request, nomFichier);
		System.out.println("PROPOSITION DE MATERIEL");
		this.getServletContext().getRequestDispatcher("/home").forward(request, response);
	}

}
