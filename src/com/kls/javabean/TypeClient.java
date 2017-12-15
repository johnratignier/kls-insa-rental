package com.kls.javabean;

import java.io.Serializable;

public class TypeClient implements Serializable{

	protected Integer id_type_client;
	protected String intitule_type_client;
	protected Double taux_remise_client;

	public TypeClient(){

	}
	
	public TypeClient(Integer id){
		this.id_type_client = id;
	}
	
	public TypeClient(Integer id, String intitule, Double taux){
		this.id_type_client = id;
		this.intitule_type_client = intitule;
		this.taux_remise_client = taux;
	}

	public Integer getId_type_client() {
		return id_type_client;
	}

	public void setId_type_client(Integer id_type_client) {
		this.id_type_client = id_type_client;
	}

	public String getIntitule_type_client() {
		return intitule_type_client;
	}

	public void setIntitule_type_client(String intitule_type_client) {
		this.intitule_type_client = intitule_type_client;
	}

	public Double getTaux_remise_client() {
		return taux_remise_client;
	}

	public void setTaux_remise_client(Double taux_remise_client) {
		this.taux_remise_client = taux_remise_client;
	}

}
