package com.kls.javabean;

import java.io.Serializable;

public class Client implements Serializable{

	protected Integer id_client;
	protected String nom_client;
	protected String description_client;
	protected TypeClient type_client;
	protected String numero_telephone_client;
	protected String mail_client;
	protected String photo_client;
	protected String facturation_adresse;
	protected String facturation_code_postal;
	protected String facturation_ville;

	public Client(){

	}

	public Client(Integer id){
		this.id_client = id;
	}
	
	public Client(Integer id, String nom, String description, TypeClient type, String numero, String mail, String photo, String facad, String faccp, String facvi){
		this.id_client = id;
		this.nom_client = nom;
		this.description_client = description;
		this.type_client = type;
		this.numero_telephone_client = numero;
		this.mail_client = mail;
		this.photo_client = photo;
		this.facturation_adresse = facad;
		this.facturation_code_postal = faccp;
		this.facturation_ville = facvi;
	}



	public String getFacturation_adresse() {
		return facturation_adresse;
	}

	public void setFacturation_adresse(String facturation_adresse) {
		this.facturation_adresse = facturation_adresse;
	}

	public String getFacturation_code_postal() {
		return facturation_code_postal;
	}

	public void setFacturation_code_postal(String facturation_code_postal) {
		this.facturation_code_postal = facturation_code_postal;
	}

	public String getFacturation_ville() {
		return facturation_ville;
	}

	public void setFacturation_ville(String facturation_ville) {
		this.facturation_ville = facturation_ville;
	}

	public String getMail_client() {
		return mail_client;
	}


	public void setMail_client(String mail_client) {
		this.mail_client = mail_client;
	}

	public String getPhoto_client() {
		return photo_client;
	}

	public void setPhoto_client(String photo_client) {
		this.photo_client = photo_client;
	}

	public Integer getId_client() {
		return id_client;
	}

	public void setId_client(Integer id_client) {
		this.id_client = id_client;
	}

	public String getNom_client() {
		return nom_client;
	}

	public void setNom_client(String nom_client) {
		this.nom_client = nom_client;
	}

	public String getDescription_client() {
		return description_client;
	}

	public void setDescription_client(String description_client) {
		this.description_client = description_client;
	}

	public TypeClient getType_client() {
		return type_client;
	}

	public void setType_client(TypeClient type_client) {
		this.type_client = type_client;
	}

	public String getNumero_telephone_client() {
		return numero_telephone_client;
	}

	public void setNumero_telephone_client(String numero_telephone_client) {
		this.numero_telephone_client = numero_telephone_client;
	}


}
