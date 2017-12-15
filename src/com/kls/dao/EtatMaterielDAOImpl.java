package com.kls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kls.configuration.DAOFactory;
import com.kls.javabean.EtatMateriel;
import com.kls.javabean.TypeMateriel;

public class EtatMaterielDAOImpl implements EtatMaterielDAO {

	private DAOFactory daofactory;

	public EtatMaterielDAOImpl(DAOFactory daofactory){
		this.daofactory = daofactory;
	}

	public void create(EtatMateriel em) {

	}

	public ArrayList<EtatMateriel> toList() {
		ArrayList<EtatMateriel> res = null;

		Connection connexion =  null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			connexion = this.daofactory.getConnection();
			pst = connexion.prepareStatement("SELECT * FROM etat_materiel");
			rs = pst.executeQuery();

			res = new ArrayList<EtatMateriel>();
			while(rs.next()){
				res.add(new EtatMateriel(rs.getInt("id_etat_materiel"), rs.getString("intitule_etat_materiel")));
			}
			System.out.print(res.toString());

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fermeturesRessources(rs, pst, connexion);
		}

		return res;
	}

	public void update(EtatMateriel em) {

	}

	public int delete(String id) {
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
