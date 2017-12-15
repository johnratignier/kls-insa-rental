import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.kls.configuration.DAOFactory;
import com.kls.dao.MembreDAO;
import com.kls.manage.MembreManage;


public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MembreDAO membreDAO;


	public SigninServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(){
		DAOFactory f = (DAOFactory) (getServletContext().getAttribute("daoFactory"));
		this.membreDAO = (MembreDAO) f.getMembreDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("membre")!=null){
			this.getServletContext().getRequestDispatcher("/home").forward(request, response);
		}else{
			this.getServletContext().getRequestDispatcher("/WEB-INF/loginPage/signin.jsp").forward(request, response);
		};
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("mot_de_passe").equals(request.getParameter("mot_de_passe_verif"))){
			MembreManage membreManage = new MembreManage(this.membreDAO);
			request.setAttribute("erreurMDP", null);
			Part part = request.getPart("photo_profil");
			String nomFichier = null;
			for (String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ){
				/* Recherche de l'éventuelle présence du paramètre "filename". */
				if ( contentDisposition.trim().startsWith("filename") ) {
					/* Si "filename" est présent, alors renvoi de sa valeur, c'est-à-dire du nom de fichier. */
					nomFichier = contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 );
					nomFichier = nomFichier.substring(1, nomFichier.length()-1);
				}
			}
			if (nomFichier != null && !nomFichier.isEmpty()) {
				String cheminFichier = this.getServletContext().getRealPath("img/photoMembre/");
				String c = "C:/Users/UTILISATEUR/Documents/Programmation JAVA EE/kls/WebContent/img/photoMembre/";
				membreManage.uploadPhotoProfil(request, part, nomFichier, cheminFichier);
			}
			membreManage.nouveauMembre(request, nomFichier);

			this.getServletContext().getRequestDispatcher("/home").forward(request, response);
		}else{
			request.setAttribute("erreurMDP", "NON");
			this.getServletContext().getRequestDispatcher("/WEB-INF/loginPage/signin.jsp").forward(request, response);
		}
	}
}
