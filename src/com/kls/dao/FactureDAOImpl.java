package com.kls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import com.kls.configuration.DAOFactory;
import com.kls.javabean.Facture;


public class FactureDAOImpl implements FactureDAO {

	private DAOFactory daofactory;

	public FactureDAOImpl(DAOFactory daofactory){
		this.daofactory = daofactory;
	}

	public int create(String chemin) {
		Connection connexion =  null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Integer res = 0;

		try {
			connexion = this.daofactory.getConnection();
			pst = connexion.prepareStatement("INSERT INTO facture (chemin_facture) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, chemin);
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			rs.next();
			res = rs.getInt(1);

		} catch (SQLException e){
			e.printStackTrace();
		}finally{
			GestionRessourcesBDD.fermetureRessource(connexion);
			GestionRessourcesBDD.fermetureRessource(pst);
			GestionRessourcesBDD.fermetureRessource(rs);
		}
		
		return res;
	}

	public Facture find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Facture statut) {
		// TODO Auto-generated method stub

	}


	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
