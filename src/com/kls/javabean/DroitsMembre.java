package com.kls.javabean;

import java.io.Serializable;

public class DroitsMembre implements Serializable{

	protected Integer id_droits_membre;
	protected String intitule_droits_membre;

	public DroitsMembre(){
		
	}
	
	public DroitsMembre(Integer id){
		this.id_droits_membre = id;
	}
	
	public DroitsMembre(Integer id, String intitule){
		this.id_droits_membre = id;
		this.intitule_droits_membre = intitule;
	}

	public Integer getId_droits_membre() {
		return id_droits_membre;
	}

	public void setId_droits_membre(Integer id_droits_membre) {
		this.id_droits_membre = id_droits_membre;
	}

	public String getIntitule_droits_membre() {
		return intitule_droits_membre;
	}

	public void setIntitule_droits_membre(String intitule_droits_membre) {
		this.intitule_droits_membre = intitule_droits_membre;
	}


}
