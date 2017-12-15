

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.kls.configuration.DAOFactory;
import com.kls.dao.ClientDAO;
import com.kls.javabean.Client;
import com.kls.javabean.Materiel;
import com.kls.manage.ClientManage;

public class ListeClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClientDAO clientDAO;


	public ListeClientServlet() {
		super();
	}
	
	public void init(){
		DAOFactory f = (DAOFactory) (getServletContext().getAttribute("daoFactory"));
		this.clientDAO = (ClientDAO) f.getClientDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id")==null && request.getParameter("modifierclient")==null && request.getParameter("supprimerclient")==null){
			HashMap<Integer, Client> clientList = this.clientDAO.toList();
			request.getSession().setAttribute("clientListe", clientList);
			this.getServletContext().getRequestDispatcher("/WEB-INF/clientPage/clientListe.jsp").forward(request, response);
		}else if(request.getParameter("id")!=null){
			Client client = this.clientDAO.find(request.getParameter("id"));
			request.setAttribute("client", client);
			this.getServletContext().getRequestDispatcher("/WEB-INF/clientPage/profileClient.jsp").forward(request, response);
		}else if(request.getParameter("modifierclient")!=null && request.getSession().getAttribute("membre")!=null){
			Client client = this.clientDAO.find(request.getParameter("modifierclient"));
			request.setAttribute("client", client);
			this.getServletContext().getRequestDispatcher("/WEB-INF/clientPage/editionClient.jsp").forward(request, response);
		}else if(request.getParameter("supprimerclient")!=null && request.getSession().getAttribute("membre")!=null){
			this.clientDAO.delete(request.getParameter("supprimerclient"));
			response.sendRedirect("/kls/listeclients");
		}else{
			response.sendRedirect("/kls/listeclients");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClientManage clientManage = new ClientManage(this.clientDAO);
		clientManage.updateClient(request);
		System.out.println("MODIFICATION CLIENT");
		response.sendRedirect("/kls/listeclients");
	}

}
