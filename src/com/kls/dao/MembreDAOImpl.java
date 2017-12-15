package com.kls.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.kls.configuration.DAOFactory;
import com.kls.javabean.Client;
import com.kls.javabean.DroitsMembre;
import com.kls.javabean.Membre;
import com.kls.javabean.Reservation;
import com.kls.javabean.Statut;
import com.kls.javabean.TypeClient;

public class MembreDAOImpl implements MembreDAO{

	private DAOFactory daofactory;
	
	public MembreDAOImpl(DAOFactory daofactory){
		this.daofactory = daofactory;
	}
	
	public void create(Membre mb) {
		Connection connexion =  null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			connexion = this.daofactory.getConnection();
			pst = connexion.prepareStatement("INSERT INTO membre (prenom, nom, adresse_mail, numero_tel, date_naissance, mot_de_passe, annee_etude, departement_etude, pseudo_artiste, photo_profil)  "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			pst.setString(1, mb.getPrenom());
			pst.setString(2, mb.getNom());
			pst.setString(3, mb.getAdresse_mail());
			pst.setString(4, mb.getNumero_tel());
			pst.setDate(5, mb.getDate_naissance());
			pst.setString(6, mb.getMot_de_passe());
			pst.setString(7, mb.getDepartement_annee());
			pst.setString(8, mb.getDepartement_etude());
			pst.setString(9, mb.getPseudo_artiste());
			pst.setString(10, mb.getPhoto_profil());
			
			pst.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			fermetureRessource(pst);
			fermetureRessource(connexion);
		}
		
	}
	
	public HashMap<Integer, Membre> toList() {
		HashMap<Integer, Membre> res = null;
		Connection connexion = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			connexion = this.daofactory.getConnection();
			pst = connexion.prepareStatement("SELECT * FROM membre "
							+ "LEFT JOIN droits_membre ON membre.droits = droits_membre.id_droits_membre "
							+ "LeFT JOIN statut ON membre.statut = statut.id_statut ");
			rs = pst.executeQuery();

			res = new HashMap<>();

			while(rs.next()){
				res.put(rs.getInt("id_membre") ,new Membre(rs.getInt("id_membre"), rs.getString("prenom"), rs.getString("nom"), rs.getString("adresse_mail"), rs.getString("numero_tel"),
						rs.getString("departement_etude"), rs.getString("annee_etude"), rs.getString("pseudo_artiste"), rs.getDate("date_naissance"), rs.getString("photo_profil"),
						new Statut(rs.getInt("id_statut"), rs.getString("intitule_statut")),
						new DroitsMembre(rs.getInt("id_droits_membre"), rs.getString("intitule_droits_membre"))));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fermeturesRessources(rs, pst, connexion);
		}
		
		return res;
}

	public Membre find(String mail) {
		
		Membre res = new Membre();
		Connection connexion =  null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			connexion = this.daofactory.getConnection();
			st = connexion.createStatement();
			
			rs = st.executeQuery("SELECT * FROM membre "
							+ "LEFT JOIN droits_membre ON membre.droits = droits_membre.id_droits_membre "
							+ "LEFT JOIN statut ON membre.statut = statut.id_statut "
							+ " WHERE adresse_mail = \""+mail+"\"");
			if(!rs.next()){
				System.out.println("PAS DE RES");
			}else{
				this.mapMembre(rs, res);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			fermeturesRessources(rs, st, connexion);
		}
		
		return res;
	}
	
	public Membre find(Integer id) {
		
		Membre res = new Membre();
		Connection connexion =  null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			connexion = this.daofactory.getConnection();
			st = connexion.createStatement();
			
			rs = st.executeQuery("SELECT * FROM membre "
							+ "LEFT JOIN droits_membre ON membre.droits = droits_membre.id_droits_membre "
							+ "LEFT JOIN statut ON membre.statut = statut.id_statut "
							+ " WHERE id_membre = "+id);
			if(!rs.next()){
				System.out.println("PAS DE RES");
			}else{
				this.mapMembre(rs, res);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			fermeturesRessources(rs, st, connexion);
		}
		
		return res;
		
	}

	public void update(Membre mb, Integer id) {
		Connection connexion =  null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			connexion = this.daofactory.getConnection();
			pst = connexion.prepareStatement("UPDATE membre SET prenom = ?, nom = ?, adresse_mail = ?, numero_tel = ?, date_naissance = ?, mot_de_passe = ?, annee_etude = ?, departement_etude = ?, pseudo_artiste = ? WHERE id_membre = "+id);
			
			pst.setString(1, mb.getPrenom());
			pst.setString(2, mb.getNom());
			pst.setString(3, mb.getAdresse_mail());
			pst.setString(4, mb.getNumero_tel());
			pst.setDate(5, mb.getDate_naissance());
			pst.setString(6, mb.getMot_de_passe());
			pst.setString(7, mb.getDepartement_annee());
			pst.setString(8, mb.getDepartement_etude());
			pst.setString(9, mb.getPseudo_artiste());
			pst.setString(10, mb.getPhoto_profil());
			
			pst.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			fermetureRessource(pst);
			fermetureRessource(connexion);
		}
	}
	
	public void updateEx(Membre mb) {
		Connection connexion =  null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			connexion = this.daofactory.getConnection();
			pst = connexion.prepareStatement("UPDATE membre SET prenom = ?, nom = ?, adresse_mail = ?, numero_tel = ?, date_naissance = ?, annee_etude = ?, departement_etude = ?, pseudo_artiste = ?, statut = ?, droits = ?  WHERE id_membre = "+mb.getId_user());
			
			pst.setString(1, mb.getPrenom());
			pst.setString(2, mb.getNom());
			pst.setString(3, mb.getAdresse_mail());
			pst.setString(4, mb.getNumero_tel());
			pst.setDate(5, mb.getDate_naissance());
			pst.setString(6, mb.getDepartement_annee());
			pst.setString(7, mb.getDepartement_etude());
			pst.setString(8, mb.getPseudo_artiste());
			pst.setInt(9, mb.getStatut().getId_statut());
			pst.setInt(10, mb.getDroits().getId_droits_membre());
			
			pst.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			fermetureRessource(pst);
			fermetureRessource(connexion);
		}
	}
	
	public void updateMDP(Integer id, String mdp) {
		Connection connexion =  null;
		PreparedStatement pst = null;
		
		try {
			connexion = this.daofactory.getConnection();
			pst = connexion.prepareStatement("UPDATE membre SET mot_de_passe = ? WHERE id_membre = "+id);
			
			pst.setString(1, mdp);
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
	
	protected void mapMembre(ResultSet rs, Membre mb) throws SQLException{
		DroitsMembre droitsMembre = new DroitsMembre(rs.getInt("id_droits_membre"), rs.getString("intitule_droits_membre"));
		Statut statut = new Statut(rs.getInt("id_statut"), rs.getString("intitule_statut"));
		mb.setPrenom(rs.getString("prenom"));
		mb.setNom(rs.getString("nom"));
		mb.setAdresse_mail(rs.getString("adresse_mail"));
		mb.setId_user(rs.getInt("id_membre"));
		mb.setNumero_tel(rs.getString("numero_tel"));
		mb.setDate_naissance(rs.getDate("date_naissance"));
		mb.setMot_de_passe(rs.getString("mot_de_passe"));
		mb.setDepartement_annee(rs.getString("annee_etude"));
		mb.setDepartement_etude(rs.getString("departement_etude"));
		mb.setPseudo_artiste(rs.getString("pseudo_artiste"));
		mb.setPhoto_profil(rs.getString("photo_profil"));
		mb.setDroits(droitsMembre);
		mb.setStatut(statut);
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
