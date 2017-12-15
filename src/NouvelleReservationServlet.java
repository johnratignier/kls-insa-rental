

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.xml.sax.SAXException;

import com.itextpdf.text.DocumentException;
import com.kls.configuration.DAOFactory;
import com.kls.dao.ClientDAO;
import com.kls.dao.EtatMaterielDAO;
import com.kls.dao.FactureDAO;
import com.kls.dao.MaterielDAO;
import com.kls.dao.ReservationDAO;
import com.kls.dao.TypeMaterielDAO;
import com.kls.javabean.Client;
import com.kls.javabean.Facture;
import com.kls.javabean.Materiel;
import com.kls.javabean.Membre;
import com.kls.javabean.Reservation;
import com.kls.manage.LocationPDF;
import com.kls.manage.ReservationManage;


public class NouvelleReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReservationDAO reservationDAO;
	private ClientDAO clientDAO;
	private MaterielDAO materielDAO;
	private FactureDAO factureDAO;


	public NouvelleReservationServlet() {
		super();

	}

	public void init(){
		DAOFactory f = (DAOFactory) (getServletContext().getAttribute("daoFactory"));
		this.reservationDAO =  (ReservationDAO) f.getReservationDAO();
		this.clientDAO = (ClientDAO) f.getClientDAO();
		this.materielDAO = (MaterielDAO) f.getMaterielDAO();
		this.factureDAO = (FactureDAO) f.getFactureDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Si membre non connecté
		if(request.getSession().getAttribute("membre")==null){
			this.getServletContext().getRequestDispatcher("/login").forward(request, response);
		}else{
			//Si réservation non demaré
			if(request.getSession().getAttribute("selectionMaterielReservation")==null ){
				System.out.println("ETAP1!");
				HashMap<Integer, Client> opt_client = this.clientDAO.toList();
				request.setAttribute("opt_client", opt_client);
				this.getServletContext().getRequestDispatcher("/WEB-INF/reservationPage/nouvelleReservation.jsp").forward(request, response);
			}else{
				//Si ajout de materiel a la reservation courante (AJAX)
				if(request.getParameter("plus")!=null){
					System.out.println("ETAP22");
					Materiel materielChoix = this.materielDAO.find(request.getParameter("plus"));
					HashMap<Integer, Materiel> selectionMateriel = (HashMap<Integer, Materiel>) request.getSession().getAttribute("selectionMaterielReservation");
					selectionMateriel.put(Integer.parseInt(request.getParameter("plus")), materielChoix);
					request.getSession().setAttribute("selectionMaterielReservation", selectionMateriel);
				//Si retrait de materiel a la reservation courante (AJAX)
				}else if(request.getParameter("moins")!=null){
					HashMap<Integer, Materiel> selectionMateriel = (HashMap<Integer, Materiel>) request.getSession().getAttribute("selectionMaterielReservation");
					selectionMateriel.remove(Integer.parseInt(request.getParameter("moins")));
					request.getSession().setAttribute("selectionMaterielReservation", selectionMateriel);
				//Si validation de la reservation
				}else if(request.getParameter("valider")!=null && request.getParameter("valider").equals("1")){
					System.out.println("A1-->");
					HttpSession session = request.getSession();
					Reservation reservation = (Reservation) session.getAttribute("reservation");
					HashMap<Integer, Materiel> selectionMateriel = (HashMap<Integer, Materiel>) session.getAttribute("selectionMaterielReservation");
					ReservationManage manage = new ReservationManage(this.reservationDAO);
					String cheminFact = reservation.getDepart_reservation().toString()+" - "+reservation.getClient_reservation().getNom_client()+" - "+reservation.getId_reservation();
					int numfacture = this.factureDAO.create(cheminFact);
					reservation.setFacture_reservation(new Facture(numfacture, cheminFact));
					manage.updateReservation(reservation, selectionMateriel);
					int id = reservation.getId_reservation();
					session.setAttribute("reservation", null);
					session.setAttribute("reservation", this.reservationDAO.find(id));
					String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					int annee = LocalDate.now().getYear(); //format(DateTimeFormatter.BASIC_ISO_DATE);
					request.setAttribute("datedujour", date);
					request.setAttribute("anneecourante", annee);
					JSPtoHTMLResponse rrr = new JSPtoHTMLResponse(response);
					request.getRequestDispatcher("/WEB-INF/templatePDF/templateFactureReservationPDF.jsp").forward(request, rrr);
					LocationPDF a = new LocationPDF();
					System.out.println("A2-->");
					try {
						String c = this.getServletContext().getRealPath("facturePDF/");
						a.generationPDF(c, rrr.getOutput(), cheminFact);
					} catch (SAXException e) {
						e.printStackTrace();
					} catch (DocumentException e) {
						e.printStackTrace();
					}
					System.out.println("A3-->");
					session.setAttribute("reservation", null);
					session.setAttribute("selectionMaterielReservation", null);
					session.setAttribute("clientReservation", null);
					session.setAttribute("reservation", null);
					System.out.println("A4-->");
					response.sendRedirect("/kls/listereservations");
					System.out.println("VALIDER");
				//Si annulation de la reservation
				}else if(request.getParameter("annuler")!=null && request.getParameter("annuler").equals("1")){
					request.getSession().setAttribute("selectionMaterielReservation", null);
					request.getSession().setAttribute("clientReservation", null);
					request.getSession().setAttribute("reservation", null);
					response.sendRedirect("/kls/home");
				}else{
					response.sendRedirect("/kls/materiel");
				}
				System.out.println("OKOK!");
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("client")+"<-->");
		HashMap<Integer, Materiel> materielChoix = new HashMap<Integer, Materiel>(); 
		Client client = this.clientDAO.find(request.getParameter("client"));
		Reservation reservation = new Reservation(client, (Membre)request.getSession().getAttribute("membre"),Date.valueOf(request.getParameter("date_depart")), Date.valueOf(request.getParameter("date_retour")), Integer.parseInt(request.getParameter("duree")));
		if(request.getParameter("commentaires")!=null){
			reservation.setCommentaires(request.getParameter("commentaires"));
		}		
		Reservation reservation2 = this.reservationDAO.create(reservation);
		reservation2.setClient_reservation(client);
		reservation2.setMembre_reservation((Membre)request.getSession().getAttribute("membre"));
		
		request.getSession().setAttribute("selectionMaterielReservation", materielChoix);
		request.getSession().setAttribute("clientReservation", client);
		request.getSession().setAttribute("reservation", reservation2);
		response.sendRedirect("/kls/materiel");
		System.out.println("fait");
	}

}
