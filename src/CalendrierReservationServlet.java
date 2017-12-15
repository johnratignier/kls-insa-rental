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
import com.kls.dao.ReservationDAO;
import com.kls.javabean.Materiel;
import com.kls.javabean.Reservation;
import com.kls.manage.MaterielManage;


public class CalendrierReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MaterielDAO materielDAO;
	private ReservationDAO reservationDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CalendrierReservationServlet() {
		super();
	}

	public void init(){
		DAOFactory f = (DAOFactory) (getServletContext().getAttribute("daoFactory"));
		this.materielDAO = (MaterielDAO) f.getMaterielDAO();
		this.reservationDAO = (ReservationDAO) f.getReservationDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<Integer, Reservation> list = this.reservationDAO.toList();
		request.setAttribute("reservation", list);
		this.getServletContext().getRequestDispatcher("/WEB-INF/reservationPage/reservationCalendrier.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
