package com.kls.javabean;

import java.io.Serializable;

public class EtatMateriel implements Serializable{

	protected Integer id_etat_materiel;
	protected String intitule_etat_materiel;

	public EtatMateriel(Integer id, String intitule){
		this.id_etat_materiel = id;
		this.intitule_etat_materiel = intitule;
	}
	
	public EtatMateriel(Integer id){
		this.id_etat_materiel = id;
		this.intitule_etat_materiel = "voir id_"+id;
	}

	public Integer getId_etat_materiel() {
		return id_etat_materiel;
	}

	public void setId_etat_materiel(Integer id_etat_materiel) {
		this.id_etat_materiel = id_etat_materiel;
	}

	public String getIntitule_etat_materiel() {
		return intitule_etat_materiel;
	}

	public void setIntitule_etat_materiel(String intitule_etat_materiel) {
		this.intitule_etat_materiel = intitule_etat_materiel;
	}



}
