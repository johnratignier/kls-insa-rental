package com.kls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.kls.configuration.DAOFactory;
import com.kls.javabean.Client;
import com.kls.javabean.DroitsMembre;
import com.kls.javabean.EtatMateriel;
import com.kls.javabean.TypeMateriel;

public class DroitsMembreDAOImpl implements DroitsMembreDAO {

	private DAOFactory daofactory;

	public DroitsMembreDAOImpl(DAOFactory daofactory){
		this.daofactory = daofactory;
	}


	public void create(DroitsMembre droits) {
		// TODO Auto-generated method stub

	}

	public DroitsMembre find(String id) {
		// TODO Auto-generated method stub
		return null;
	}


	public ArrayList<DroitsMembre> toList() {
		ArrayList<DroitsMembre> res = null;
		Connection connexion = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			connexion = this.daofactory.getConnection();
			pst = connexion.prepareStatement("SELECT * FROM droits_membre");
			rs = pst.executeQuery();

			res = new ArrayList<DroitsMembre>();
			while(rs.next()){
				res.add(new DroitsMembre(rs.getInt("id_droits_membre"), rs.getString("intitule_droits_membre")));
			}
			System.out.print(res.toString());
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			GestionRessourcesBDD.fermeturesRessources(rs, pst,  connexion);
		}

		return res;
	}


	public void update(DroitsMembre client) {
		// TODO Auto-generated method stub

	}


	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}



}
