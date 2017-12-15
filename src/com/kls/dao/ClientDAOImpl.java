package com.kls.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.kls.configuration.DAOFactory;
import com.kls.javabean.Client;
import com.kls.javabean.EtatMateriel;
import com.kls.javabean.Materiel;
import com.kls.javabean.TypeClient;
import com.kls.javabean.TypeMateriel;

public class ClientDAOImpl implements ClientDAO{

	private DAOFactory daofactory;

	public ClientDAOImpl(DAOFactory daofactory){
		this.daofactory = daofactory;
	}

	public ClientDAOImpl(){

	}

	public void create(Client cli) {
		Connection connexion =  null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			connexion = this.daofactory.getConnection();
			pst = connexion.prepareStatement("INSERT INTO client (nom_client, description_client, type, numero_tel_client, adresse_mail, photo_client, facturation_adresse, facturation_code_postal, facturation_ville)  "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

			pst.setString(1, cli.getNom_client());
			pst.setString(2, cli.getDescription_client());
			pst.setInt(3, cli.getType_client().getId_type_client());
			pst.setString(4, cli.getNumero_telephone_client());
			pst.setString(5, cli.getMail_client());
			pst.setString(6, cli.getPhoto_client());
			pst.setString(7, cli.getFacturation_adresse());
			pst.setString(8, cli.getFacturation_code_postal());
			pst.setString(9, cli.getFacturation_ville());

			pst.execute();

		} catch (SQLException e){
			e.printStackTrace();
		}finally{
			fermetureRessource(pst);
			fermetureRessource(connexion);
		}
	}

	public Client find(String id) {
		Client res = null;
		TypeClient type;
		Connection connexion =  null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			connexion = this.daofactory.getConnection();
			pst = connexion.prepareStatement("SELECT * FROM client "
					+ "LEFT JOIN type_client ON client.type = type_client.id_type_client "
					+ "WHERE client.id_client = \""+id+"\"");
			rs = pst.executeQuery();
			rs.next();
			
			res = new Client();
			type = new TypeClient(rs.getInt("id_type_client"), rs.getString("intitule_type_client"), rs.getDouble("taux_remise"));
			
			res.setDescription_client(rs.getString("description_client"));
			res.setFacturation_adresse(rs.getString("facturation_adresse"));
			res.setFacturation_code_postal(rs.getString("facturation_code_postal"));
			res.setFacturation_ville(rs.getString("facturation_ville"));
			res.setMail_client(rs.getString("adresse_mail"));
			res.setPhoto_client(rs.getString("photo_client"));
			res.setId_client(rs.getInt("id_client"));
			res.setNom_client(rs.getString("nom_client"));
			res.setNumero_telephone_client(rs.getString("numero_tel_client"));
			res.setType_client(type);

		} catch (SQLException e){
			e.printStackTrace();
		}finally{
			fermetureRessource(pst);
			fermetureRessource(connexion);
		}
		return res;
	}

	public HashMap<Integer, Client> toList() {
		HashMap<Integer, Client> res = null;
		Connection connexion = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			connexion = this.daofactory.getConnection();
			pst = connexion.prepareStatement("SELECT * FROM client "
							+ "LEFT JOIN type_client ON client.type = type_client.id_type_client");
			rs = pst.executeQuery();

			res = new HashMap<>();

			while(rs.next()){
				res.put(rs.getInt("id_client") ,new Client(rs.getInt("id_client"), rs.getString("nom_client"), rs.getString("description_client"),
						new TypeClient(rs.getInt("id_type_client"), rs.getString("intitule_type_client"), rs.getDouble("taux_remise")),
						rs.getString("numero_tel_client"), rs.getString("adresse_mail"), rs.getString("photo_client"), rs.getString("facturation_adresse"), rs.getString("facturation_code_postal"), rs.getString("facturation_ville")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fermeturesRessources(rs, pst, connexion);
		}
		
		return res;
	}

	public void update(Client client) {
		Connection connexion =  null;
		PreparedStatement pst = null;
		
		try {
			connexion = this.daofactory.getConnection();
			pst = connexion.prepareStatement("UPDATE client SET nom_client = ?, description_client = ?, numero_tel_client = ?, adresse_mail = ?, facturation_adresse = ?, facturation_code_postal = ?, facturation_ville = ?, type = ? WHERE id_client = "+client.getId_client());
			
			pst.setString(1, client.getNom_client());
			pst.setString(2, client.getDescription_client());
			pst.setString(3,  client.getNumero_telephone_client());
			pst.setString(4,  client.getMail_client());
			pst.setString(5, client.getFacturation_adresse());
			pst.setString(6, client.getFacturation_code_postal());
			pst.setString(7, client.getFacturation_ville());
			pst.setInt(8, client.getType_client().getId_type_client());
			
			pst.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			fermetureRessource(pst);
			fermetureRessource(connexion);
		}
	}

	public int delete(String id) {
		Connection connexion =  null;
		PreparedStatement pst = null;

		try {
			connexion = this.daofactory.getConnection();
			pst = connexion.prepareStatement("DELETE FROM client WHERE id_client="+id);
			pst.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			fermetureRessource(pst);
			fermetureRessource(connexion);
		}
		return 0;
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