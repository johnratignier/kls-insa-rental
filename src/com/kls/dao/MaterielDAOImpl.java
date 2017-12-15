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
import java.util.Iterator;
import java.util.Map;

import com.kls.configuration.DAOFactory;
import com.kls.javabean.EtatMateriel;
import com.kls.javabean.Materiel;
import com.kls.javabean.TypeMateriel;

public class MaterielDAOImpl implements MaterielDAO{

	private DAOFactory daofactory;

	public MaterielDAOImpl(DAOFactory daofactory){
		this.daofactory = daofactory;
	}

	public MaterielDAOImpl(){

	}

	public void create(Materiel mat) {
		Connection connexion =  null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			connexion = this.daofactory.getConnection();
			pst = connexion.prepareStatement("INSERT INTO materiel (nom, description, prix, caution, etat, type, photo_materiel, location, exoneration)  "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

			pst.setString(1, mat.getNom_materiel());
			pst.setString(2, mat.getDescription_materiel());
			pst.setDouble(3, mat.getPrix_materiel());
			pst.setDouble(4, mat.getCaution_materiel());
			pst.setInt(5, mat.getEtat_materiel().getId_etat_materiel());
			pst.setInt(6, mat.getType_materiel().getId_type_materiel());
			pst.setString(7, mat.getPhoto_materiel());
			pst.setInt(8, mat.getLocation());
			pst.setInt(9, mat.getExoneration());

			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			fermetureRessource(pst);
			fermetureRessource(connexion);
		}
	}

	public Materiel find(String id) {
		Materiel res = null;
		Connection connexion = null;
		PreparedStatement pst = null, pst2 = null;
		ResultSet rs = null, rs2 = null;
		try {
			connexion = this.daofactory.getConnection();
			pst = connexion.prepareStatement("SELECT * FROM materiel "
							+ "LEFT JOIN etat_materiel ON materiel.etat = etat_materiel.id_etat_materiel "
							+ "LEFT JOIN type_materiel ON materiel.type = type_materiel.id_type_materiel "
							+ "LEFT JOIN date_materiel ON materiel.id_materiel = date_materiel.id_materiel "
							+ "WHERE materiel.id_materiel = \""+id+"\"");
			rs = pst.executeQuery();
			
			rs.next();			
			res = new Materiel(rs.getInt("id_materiel"), rs.getString("nom"), rs.getString("description"), rs.getDouble("prix"), rs.getDouble("caution"), 
					new EtatMateriel(rs.getInt("id_etat_materiel"),rs.getString("intitule_etat_materiel")),
					new TypeMateriel(rs.getInt("id_type_materiel"),rs.getString("intitule_type_materiel")), rs.getString("photo_materiel"), rs.getInt("location"), rs.getInt("exoneration"));
			ArrayList<Date> dateList = new ArrayList<Date>();
			
			pst2 = connexion.prepareStatement("SELECT date_materiel.date FROM date_materiel "
							+ "WHERE date_materiel.id_materiel = "+id);
			rs2 = pst2.executeQuery();
			
			while(rs2.next()){
				System.out.println("AJOUT ");
				dateList.add(rs2.getDate("date"));
			}
			res.setListe_date_reservation(dateList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			fermeturesRessources(rs, pst, connexion);
			fermetureRessource(pst2);
			fermetureRessource(rs2);
		}
			
		return res;
	}

	public void update(Materiel mat) {
		Connection connexion = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			connexion = this.daofactory.getConnection();
			pst = connexion.prepareStatement("UPDATE materiel SET nom = ?, description = ?, prix = ?, caution = ?, location = ?, etat = ?, type = ?, exoneration = ? WHERE id_materiel = "+mat.getId_materiel());
			
			pst.setString(1, mat.getNom_materiel());
			pst.setString(2, mat.getDescription_materiel());
			pst.setDouble(3, mat.getPrix_materiel());
			pst.setDouble(4, mat.getCaution_materiel());
			pst.setInt(5, mat.getLocation());
			pst.setInt(6, mat.getEtat_materiel().getId_etat_materiel());
			pst.setInt(7, mat.getType_materiel().getId_type_materiel());
			pst.setInt(8, mat.getExoneration());
			
			pst.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			fermetureRessource(pst);
			fermetureRessource(connexion);
		}
		
	}

	public int delete(String id) {
		return 0;
	}

	public HashMap<Integer, Materiel> toList() {
		HashMap<Integer, Materiel> res = null;
		Connection connexion = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			connexion = this.daofactory.getConnection();
			pst = connexion.prepareStatement("SELECT * FROM materiel "
							+ "LEFT JOIN etat_materiel ON materiel.etat = etat_materiel.id_etat_materiel "
							+ "LEFT JOIN type_materiel ON materiel.type = type_materiel.id_type_materiel");
			rs = pst.executeQuery();
			
			res = new HashMap<>();
			
			while(rs.next()){
				Materiel in = new Materiel(rs.getInt("id_materiel"), rs.getString("nom"), rs.getString("description"),
						rs.getDouble("prix"), rs.getDouble("caution"), 
						new EtatMateriel(rs.getInt("id_etat_materiel"),rs.getString("intitule_etat_materiel")),
						new TypeMateriel(rs.getInt("id_type_materiel"),rs.getString("intitule_type_materiel")),
						rs.getString("photo_materiel"), rs.getInt("location"), rs.getInt("exoneration"));
				res.put(rs.getInt("id_materiel"), in);				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fermeturesRessources(rs, pst, connexion);
		}
		return res;
	}
	
	public HashMap<Integer, Materiel> toList(Date arrivee, Date retour) {
		HashMap<Integer, Materiel> res = null;
		Long curtime = arrivee.getTime();
		Long endtime = retour.getTime();
		ArrayList<Date> listeDateReservation = new ArrayList<Date>();
		while(curtime <= endtime){
			listeDateReservation.add(new Date(curtime));
			curtime += (24*1000*60*60);
		}

		Connection connexion = null;
		PreparedStatement pst = null, pst2 = null;
		ResultSet rs = null, rs2 = null;
		
		try {
			connexion = this.daofactory.getConnection();
			pst = connexion.prepareStatement("SELECT * FROM materiel "
							+ "LEFT JOIN etat_materiel ON materiel.etat = etat_materiel.id_etat_materiel "
							+ "LEFT JOIN type_materiel ON materiel.type = type_materiel.id_type_materiel");
			rs = pst.executeQuery();
			
			res = new HashMap<>();
			
			while(rs.next()){
				Materiel in = new Materiel(rs.getInt("id_materiel"), rs.getString("nom"), rs.getString("description"),
						rs.getDouble("prix"), rs.getDouble("caution"), 
						new EtatMateriel(rs.getInt("id_etat_materiel"),rs.getString("intitule_etat_materiel")),
						new TypeMateriel(rs.getInt("id_type_materiel"),rs.getString("intitule_type_materiel")),
						rs.getString("photo_materiel"), rs.getInt("location"), rs.getInt("exoneration"));
								
				pst2 = connexion.prepareStatement("SELECT date_materiel.date FROM date_materiel "
								+ "WHERE date_materiel.id_materiel = "+in.getId_materiel());
				rs2 = pst2.executeQuery();
				int stop=0;
				while(rs2.next() && stop==0){
					Date date = rs2.getDate("date");
					Iterator<Date> iterator = listeDateReservation.iterator();
					while(iterator.hasNext()){
						if(iterator.next().equals(date)){
							in.setUtilisation_reservation(1);
							stop++;
						}
					}
				}
				res.put(rs.getInt("id_materiel"), in);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fermeturesRessources(rs, pst, connexion);
			fermetureRessource(pst2);
			fermetureRessource(rs2);
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
