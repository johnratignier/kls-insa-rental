package com.kls.javabean;

import java.io.Serializable;

public class TypeMateriel implements Serializable{

	protected Integer id_type_materiel;
	protected String intitule_type_materiel;

	public TypeMateriel(Integer id, String intitule){
		this.id_type_materiel = id;
		this.intitule_type_materiel = intitule;
	}
	
	public TypeMateriel(Integer id){
		this.id_type_materiel = id;
		this.intitule_type_materiel = "voir id_"+id;
	}

	public Integer getId_type_materiel() {
		return id_type_materiel;
	}

	public void setId_type_materiel(Integer id_type_materiel) {
		this.id_type_materiel = id_type_materiel;
	}

	public String getIntitule_type_materiel() {
		return intitule_type_materiel;
	}

	public void setIntitule_type_materiel(String intitule_materiel) {
		this.intitule_type_materiel = intitule_materiel;
	}

}
