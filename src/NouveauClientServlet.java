

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.kls.configuration.DAOFactory;
import com.kls.dao.ClientDAO;
import com.kls.dao.EtatMaterielDAO;
import com.kls.dao.MaterielDAO;
import com.kls.dao.TypeMaterielDAO;
import com.kls.manage.ClientManage;
import com.kls.manage.MaterielManage;


public class NouveauClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClientDAO clientDAO;


	public NouveauClientServlet() {
		super();
	}
	
	public void init(){
		DAOFactory f = (DAOFactory) (getServletContext().getAttribute("daoFactory"));
		this.clientDAO =  (ClientDAO) f.getClientDAO();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/clientPage/nouveauClient.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClientManage clientManage = new ClientManage(this.clientDAO);
		Part part = request.getPart("photo_client");
		String nomFichier = null;
		for (String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
			/* Recherche de l'éventuelle présence du paramètre "filename". */
			if ( contentDisposition.trim().startsWith("filename")) {
				/* Si "filename" est présent, alors renvoi de sa valeur, c'est-à-dire du nom de fichier. */
				nomFichier = contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 );
				nomFichier = nomFichier.substring(1, nomFichier.length()-1);
			}
		}
		if ( nomFichier != null && !nomFichier.isEmpty() ) {
			String cheminFichier = this.getServletContext().getRealPath("img/photoClient/");
			String c = "C:/Users/UTILISATEUR/Documents/Programmation JAVA EE/kls/WebContent/img/photoClient/";
			clientManage.uploadPhotoClient(request, part, nomFichier, cheminFichier);
		}
		clientManage.nouveauClient(request, nomFichier);
		System.out.println("INSCRIPTION CLIENT");
		this.getServletContext().getRequestDispatcher("/listeclients").forward(request, response);
	}

}
