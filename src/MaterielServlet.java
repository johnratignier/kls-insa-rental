import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kls.configuration.DAOFactory;
import com.kls.dao.MaterielDAO;
import com.kls.dao.MembreDAO;
import com.kls.javabean.Materiel;
import com.kls.javabean.Reservation;
import com.kls.manage.MaterielManage;


public class MaterielServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MaterielDAO materielDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MaterielServlet() {
		super();
	}

	public void init(){
		DAOFactory f = (DAOFactory) (getServletContext().getAttribute("daoFactory"));
		this.materielDAO = (MaterielDAO) f.getMaterielDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("faitMATERIELPRE");
		
		if(request.getParameter("id")==null){
			HashMap<Integer, Materiel> materielList;
			if(request.getSession().getAttribute("reservation")!=null){
				Reservation r = (Reservation) request.getSession().getAttribute("reservation");
				materielList = this.materielDAO.toList(r.getDepart_reservation(), r.getRetour_reservation());
			}else{
				materielList = this.materielDAO.toList();
			}
			System.out.println("faitMATERIEL");
			request.getSession().setAttribute("materielList", materielList);
			System.out.println("Page Materiel envoyé");
			if(request.getSession().getAttribute("reservation")!=null){
				this.getServletContext().getRequestDispatcher("/WEB-INF/materielPage/materielChoix.jsp").forward(request, response);
			}else{
				this.getServletContext().getRequestDispatcher("/WEB-INF/materielPage/materiel2.jsp").forward(request, response);
			}
			System.out.println("faitMATERIEL");
			
		//Materiel particulier (AJAX)
		}else if(request.getParameter("id")!=null){
			System.out.println("faitMATERIELPOST");
			Materiel materielCurrent = this.materielDAO.find(request.getParameter("id"));
			ArrayList<Date> datelist = materielCurrent.getListe_date_reservation();
			String datelistestring = "   ";
			for(int i=0; i< datelist.size(); i++)
				datelistestring=datelistestring+"'"+datelist.get(i).toString()+"' , ";
			datelistestring = datelistestring.substring(0, datelistestring.length()-3);
			System.out.println(datelistestring);
			request.setAttribute("materielCurrent", materielCurrent);
			request.setAttribute("listedate", datelistestring);
			this.getServletContext().getRequestDispatcher("/WEB-INF/materielPage/detailMaterielFragment2.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
