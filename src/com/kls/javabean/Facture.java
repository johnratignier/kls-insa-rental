package com.kls.javabean;

import java.io.Serializable;

public class Facture implements Serializable {

	protected Integer numero_facture;
	protected String chemin_facture;

	public Facture(){

	}

	public Facture(Integer id, String intitule){
		this.numero_facture = id;
		this.chemin_facture = intitule;
	}
	
	public Facture(Integer id){
		this.numero_facture = id;
	}

	public Integer getNumero_facture() {
		return numero_facture;
	}

	public void setNumero_facture(Integer numero_facture) {
		this.numero_facture = numero_facture;
	}

	public String getChemin_facture() {
		return chemin_facture;
	}

	public void setChemin_facture(String chemin_facture) {
		this.chemin_facture = chemin_facture;
	}



}
