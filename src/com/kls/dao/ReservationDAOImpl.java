package com.kls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import com.kls.configuration.DAOFactory;
import com.kls.javabean.Client;
import com.kls.javabean.EtatMateriel;
import com.kls.javabean.Facture;
import com.kls.javabean.Materiel;
import com.kls.javabean.Membre;
import com.kls.javabean.Reservation;
import com.kls.javabean.TypeClient;
import com.kls.javabean.TypeMateriel;

public class ReservationDAOImpl implements ReservationDAO{

	private DAOFactory daofactory;

	public ReservationDAOImpl(DAOFactory daofactory){
		this.daofactory = daofactory;
	}

	public ReservationDAOImpl(){

	}

	public Reservation create(Reservation res){
		Reservation resultat = new Reservation();
		Connection connexion =  null;
		PreparedStatement pst = null, pst2=null;
		ResultSet rs = null;

		try {
			connexion = this.daofactory.getConnection();
			pst = connexion.prepareStatement("INSERT INTO reservation (client, membre, depart, retour, duree, commentaires) "
					+ "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			pst.setInt(1, res.getClient_reservation().getId_client());
			pst.setInt(2, res.getMembre_reservation().getId_user());
			pst.setDate(3, res.getDepart_reservation());
			pst.setDate(4, res.getRetour_reservation());
			pst.setInt(5, res.getDuree_reservation());
			pst.setString(6, res.getCommentaires());
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			rs.next();
			Integer idrow = rs.getInt(1);
			System.out.println("CREATION: "+rs.getInt(1));
			
			resultat.setCommentaires(res.getCommentaires());
			resultat.setDuree_reservation(res.getDuree_reservation());
			resultat.setDepart_reservation(res.getDepart_reservation());
			resultat.setRetour_reservation(res.getRetour_reservation());
			resultat.setId_reservation(idrow);
			resultat.setTotal_prix_reservation(0.00);
			resultat.setTotal_caution_reservation(0.00);
			resultat.setTotal_prix_avant_remise_reservation(0.00);

		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			fermetureRessource(rs);
			fermetureRessource(pst);
			fermetureRessource(pst2);
			fermetureRessource(connexion);
		}
		return resultat;
		
	}

	public Reservation find(Integer id) {
		Reservation res = new Reservation();
		Connection connexion =  null;
		Statement st = null;
		ResultSet rs = null;

		try {
			connexion = this.daofactory.getConnection();
			st = connexion.createStatement();

			rs = st.executeQuery("SELECT * FROM reservation "
					+ "LEFT JOIN membre ON membre.id_membre = reservation.membre "
					+ "LEFT JOIN client ON client.id_client = reservation.client "
					+ "LEFT JOIN type_client ON type_client.id_type_client = client.type "
					+ "LEFT JOIN facture ON facture.numero_facture = reservation.facture "
					+ "WHERE id_reservation = \""+id+"\"");

			rs.next();
			Client cli = new Client();
			Membre mem = new Membre();

			res.setId_reservation(rs.getInt("id_reservation"));
			res.setCommentaires(rs.getString("commentaires"));
			res.setDepart_reservation(rs.getDate("depart"));
			res.setRetour_reservation(rs.getDate("retour"));
			res.setDuree_reservation(rs.getInt("duree"));
			res.setTotal_caution_reservation(rs.getDouble("caution"));
			res.setTotal_prix_reservation(rs.getDouble("total"));
			res.setTotal_prix_avant_remise_reservation(rs.getDouble("total_avant_remise"));
			res.setFacture_reservation(new Facture(rs.getInt("facture"), rs.getString("chemin_facture")));

			cli.setNom_client(rs.getString("nom_client"));
			cli.setId_client(rs.getInt("id_client"));
			cli.setDescription_client(rs.getString("description_client"));
			cli.setNumero_telephone_client(rs.getString("numero_tel_client"));
			cli.setMail_client(rs.getString("adresse_mail"));
			cli.setType_client(new TypeClient(rs.getInt("id_type_client"), rs.getString("intitule_type_client"), rs.getDouble("taux_remise")));
			cli.setFacturation_adresse(rs.getString("facturation_adresse"));
			cli.setFacturation_code_postal(rs.getString("facturation_code_postal"));
			cli.setFacturation_ville(rs.getString("facturation_ville"));

			mem.setId_user(rs.getInt("id_membre"));
			mem.setAdresse_mail(rs.getString("adresse_mail"));
			mem.setNumero_tel(rs.getString("numero_tel"));
			mem.setPrenom(rs.getString("prenom"));
			mem.setNom(rs.getString("nom"));
			mem.setPseudo_artiste(rs.getString("pseudo_artiste"));
			
			res.setMembre_reservation(mem);
			res.setClient_reservation(cli);

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			fermeturesRessources(rs, st, connexion);
		}

		res.setList_materiel_reservation(getMaterielReserve(id));

		return res;
	}

	public void update(Reservation reservation, HashMap<Integer, Materiel> matos) {
		Connection connexion =  null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			connexion = this.daofactory.getConnection();
			pst = connexion.prepareStatement("UPDATE reservation SET total = ?, caution = ?, client = ?, depart = ?, retour = ?, commentaires = ?, membre = ?, duree = ?, total_avant_remise = ?, facture = ?  WHERE id_reservation = "+reservation.getId_reservation());

			pst.setDouble(1, reservation.getTotal_prix_reservation());
			pst.setDouble(2, reservation.getTotal_caution_reservation());
			pst.setDouble(9, reservation.getTotal_prix_avant_remise_reservation());
			pst.setInt(3, reservation.getClient_reservation().getId_client());
			pst.setDate(4, reservation.getDepart_reservation());
			pst.setDate(5,  reservation.getRetour_reservation());
			pst.setString(6, reservation.getCommentaires());
			pst.setInt(7, reservation.getMembre_reservation().getId_user());
			pst.setInt(8, reservation.getDuree_reservation());
			pst.setInt(10, reservation.getFacture_reservation().getNumero_facture());
			pst.execute();

			for(Map.Entry<Integer, Materiel> entry : matos.entrySet()){
				pst = connexion.prepareStatement("INSERT INTO reservation_materiel (id_reservation, id_materiel) VALUES (?, ?)");
				pst.setInt(1, reservation.getId_reservation());
				pst.setInt(2, entry.getValue().getId_materiel());
				pst.execute();
			}
			
			Date start = reservation.getDepart_reservation();
			Long curtime = start.getTime();
			Date end = reservation.getRetour_reservation();
			Long endtime = end.getTime();
			ArrayList<Date> listedate = new ArrayList<Date>();
			while(curtime <= endtime){
				listedate.add(new Date(curtime));
				curtime += (24*1000*60*60);
			}

			for(Map.Entry<Integer, Materiel> entry : matos.entrySet()){
				for(int i=0; i<listedate.size();i++){
					pst = connexion.prepareStatement("INSERT INTO date_materiel (date, id_materiel,  id_reservation) VALUES (?, ?, ?)");
					pst.setDate(1, listedate.get(i));
					pst.setInt(2, entry.getValue().getId_materiel());
					pst.setInt(3, reservation.getId_reservation());
					pst.execute();
				}
			}
			

		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			fermetureRessource(rs);
			fermetureRessource(pst);
			fermetureRessource(connexion);
		}
	}

	public int delete(String id) {
		Connection connexion =  null;
		PreparedStatement pst = null;

		try {
			connexion = this.daofactory.getConnection();
			pst = connexion.prepareStatement("DELETE FROM reservation WHERE id_reservation="+id);
			pst.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			fermetureRessource(pst);
			fermetureRessource(connexion);
		}
		return 0;
	}

	public HashMap<Integer, Reservation> toList() {
		HashMap<Integer, Reservation> res = new HashMap<Integer, Reservation>();
		
		Connection connexion =  null;
		PreparedStatement pst = null, pst2=null;
		ResultSet rs = null;

		try {
			connexion = this.daofactory.getConnection();
			pst = connexion.prepareStatement("SELECT * FROM reservation  "
							+ "LEFT JOIN client ON client.id_client = reservation.client "
							+ "LEFT JOIN type_client ON type_client.id_type_client = client.type");

			rs = pst.executeQuery();
			
			while(rs.next()){
				Reservation reserv = new Reservation(new Client(rs.getInt("id_client"), rs.getString("nom_client"), rs.getString("description_client"),
						new TypeClient(rs.getInt("id_type_client"), rs.getString("intitule_type_client"), rs.getDouble("taux_remise")),
						rs.getString("numero_tel_client"), rs.getString("adresse_mail"), rs.getString("photo_client"), rs.getString("facturation_adresse"), rs.getString("facturation_code_postal"), rs.getString("facturation_ville")),
						new Membre(rs.getInt("membre")), rs.getDate("depart"), rs.getDate("retour"), rs.getInt("duree"), rs.getDouble("total"), rs.getDouble("caution"), rs.getString("commentaires"));
				reserv.setTotal_prix_avant_remise_reservation(rs.getDouble("total_avant_remise"));
				reserv.setList_materiel_reservation(this.getMaterielReserve(rs.getInt("id_reservation")));
				reserv.setFacture_reservation(new Facture(rs.getInt("facture")));
				res.put(rs.getInt("id_reservation"), reserv);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			fermetureRessource(rs);
			fermetureRessource(pst);
			fermetureRessource(pst2);
			fermetureRessource(connexion);
		}		
		
		return res;
	}

	public ArrayList<Materiel> getMaterielReserve(Integer id){
		ArrayList<Materiel> res = new ArrayList<Materiel>();
		Connection connexion =  null;
		Statement st = null;
		ResultSet rs = null;

		try {
			connexion = this.daofactory.getConnection();
			st = connexion.createStatement();

			rs = st.executeQuery("SELECT * FROM reservation_materiel "
					+ "LEFT JOIN materiel ON materiel.id_materiel = reservation_materiel.id_materiel "
					+ "LEFT JOIN etat_materiel ON etat_materiel.id_etat_materiel = materiel.etat "
					+ "LEFT JOIN type_materiel ON type_materiel.id_type_materiel = materiel.type "
					+ "WHERE reservation_materiel.id_reservation = \""+id+"\"");

			while(rs.next()){
				Materiel materiel = new Materiel(rs.getInt("id_materiel"), rs.getString("nom"), rs.getString("description"), rs.getDouble("prix"), rs.getDouble("caution"), 
						new EtatMateriel(rs.getInt("id_etat_materiel"), rs.getString("intitule_etat_materiel")), new TypeMateriel(rs.getInt("id_type_materiel"), rs.getString("intitule_type_materiel")));
				materiel.setLocation(rs.getInt("location"));
				materiel.setExoneration(rs.getInt("exoneration"));
				res.add(materiel);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			fermeturesRessources(rs, st, connexion);
		}

		return res;
	}



	public static void fermetureRessource(ResultSet resultSet) {
		if ( resultSet != null ) {
			try {
				resultSet.close();
			} catch ( SQLException e ) {
				System.out.println( "Échec de la fermeture du ResultSet : " + e.getMessage() );
			}
		}
	}

	public static void fermetureRessource(Statement statement) {
		if ( statement != null ) {
			try {
				statement.close();
			} catch ( SQLException e ) {
				System.out.println( "Échec de la fermeture du Statement : " + e.getMessage() );
			}
		}
	}

	public static void fermetureRessource(PreparedStatement statement) {
		if ( statement != null ) {
			try {
				statement.close();
			} catch ( SQLException e ) {
				System.out.println( "Échec de la fermeture du Statement : " + e.getMessage() );
			}
		}
	}

	public static void fermetureRessource(Connection connexion) {
		if ( connexion != null ) {
			try {
				connexion.close();
			} catch ( SQLException e ) {
				System.out.println( "Échec de la fermeture de la connexion : " + e.getMessage() );
			}
		}
	}

	public static void fermeturesRessources(ResultSet resultSet, Statement statement, Connection connexion) {
		fermetureRessource( resultSet );
		fermetureRessource( statement );
		fermetureRessource( connexion );
	}

}
