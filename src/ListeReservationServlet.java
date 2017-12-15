

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xml.sax.SAXException;

import com.itextpdf.text.DocumentException;
import com.kls.configuration.DAOFactory;
import com.kls.dao.ClientDAO;
import com.kls.dao.ReservationDAO;
import com.kls.javabean.Client;
import com.kls.javabean.Materiel;
import com.kls.javabean.Reservation;
import com.kls.manage.LocationPDF;

public class ListeReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReservationDAO reservationDAO;


	public ListeReservationServlet() {
		super();
	}
	
	public void init(){
		DAOFactory f = (DAOFactory) (getServletContext().getAttribute("daoFactory"));
		this.reservationDAO = (ReservationDAO) f.getReservationDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Si page de detail
		if(request.getParameter("id")!=null){
			Reservation r = this.reservationDAO.find(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("reservation", r);
			/*JSPtoHTMLResponse rrr = new JSPtoHTMLResponse(response);
			request.getRequestDispatcher("/WEB-INF/templatePDF/templateFactureReservationPDF.jsp").forward(request, rrr);
			LocationPDF a = new LocationPDF();
			try {
				String c = this.getServletContext().getRealPath("facturePDF/");
				System.out.println(c);
				a.generationPDF(c, rrr.getOutput(), r.getDepart_reservation()+" - "+r.getClient_reservation().getNom_client()+" - "+r.getId_reservation());
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			}*/
			this.getServletContext().getRequestDispatcher("/WEB-INF/reservationPage/reservationDetail.jsp").forward(request, response);
		//Si suppression reservation (AJAX)
		}else if(request.getParameter("supprimerreservation")!=null && request.getSession().getAttribute("membre")!=null){
			this.reservationDAO.delete(request.getParameter("supprimerreservation"));
			response.sendRedirect("/kls/listereservations");
		//Si modification reservation (AJAX)
		}else if(request.getParameter("modifierreservation")!=null && request.getSession().getAttribute("membre")!=null){
			
			response.sendRedirect("/kls/materiel");
		//Sinon afficher la liste
		}else{
			HashMap<Integer, Reservation> listeReservation = this.reservationDAO.toList();
			request.setAttribute("listeReservation", listeReservation);
			this.getServletContext().getRequestDispatcher("/WEB-INF/reservationPage/reservationListe.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
