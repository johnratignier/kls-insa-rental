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
import com.kls.javabean.Statut;
import com.kls.javabean.TypeMateriel;

public class StatutDAOImpl implements StatutDAO {

	private DAOFactory daofactory;

	public StatutDAOImpl(DAOFactory daofactory){
		this.daofactory = daofactory;
	}


	public void create(Statut statut) {
		// TODO Auto-generated method stub

	}

	public Statut find(String id) {
		// TODO Auto-generated method stub
		return null;
	}


	public ArrayList<Statut> toList() {
		ArrayList<Statut> res = null;
		Connection connexion = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			connexion = this.daofactory.getConnection();
			pst = connexion.prepareStatement("SELECT * FROM statut");
			rs = pst.executeQuery();

			res = new ArrayList<Statut>();
			while(rs.next()){
				res.add(new Statut(rs.getInt("id_statut"), rs.getString("intitule_statut")));
			}
			System.out.print(res.toString());

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			GestionRessourcesBDD.fermeturesRessources(rs, pst, connexion);
		}

		return res;
	}


	public void update(Statut statut) {
		// TODO Auto-generated method stub

	}


	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}



}
