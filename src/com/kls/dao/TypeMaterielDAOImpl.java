package com.kls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kls.configuration.DAOFactory;
import com.kls.javabean.TypeMateriel;
import com.kls.dao.GestionRessourcesBDD;


public class TypeMaterielDAOImpl implements TypeMaterielDAO {

	private DAOFactory daofactory;

	public TypeMaterielDAOImpl(DAOFactory daofactory){
		this.daofactory = daofactory;
	}

	public void create(TypeMateriel tm) {
		
	}

	public ArrayList<TypeMateriel> toList() {
		
		ArrayList<TypeMateriel> res = null;
		
		Connection connexion =  null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			connexion = this.daofactory.getConnection();
			pst = connexion.prepareStatement("SELECT * FROM type_materiel");
			rs = pst.executeQuery();
			
			res = new ArrayList<TypeMateriel>();
			while(rs.next()){
				res.add(new TypeMateriel(rs.getInt("id_type_materiel"), rs.getString("intitule_type_materiel")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			GestionRessourcesBDD.fermeturesRessources(rs, pst, connexion);
		}
		
		return res;
	}

	public void update(TypeMateriel tm) {
		
	}

	public int delete(String id) {
		return 0;
	}

}
