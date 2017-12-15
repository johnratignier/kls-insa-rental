package com.kls.javabean;

import java.io.Serializable;

public class Statut implements Serializable {

	protected Integer id_statut;
	protected String intitule_statut;

	public Statut(){
		
	}
	
	public Statut(Integer id, String intitule){
		this.id_statut = id;
		this.intitule_statut = intitule;
	}
	
	public Statut(Integer id){
		this.id_statut = id;
	}

	public Integer getId_statut() {
		return id_statut;
	}

	public void setId_statut(Integer id_statut) {
		this.id_statut = id_statut;
	}

	public String getIntitule_statut() {
		return intitule_statut;
	}

	public void setIntitule_statut(String intitule_statut) {
		this.intitule_statut = intitule_statut;
	}

}
