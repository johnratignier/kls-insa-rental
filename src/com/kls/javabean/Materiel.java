package com.kls.javabean;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class Materiel implements Serializable{

	protected Integer id_materiel;
	protected String nom_materiel;
	protected String description_materiel;
	protected Double prix_materiel;
	protected Double caution_materiel;
	protected EtatMateriel  etat_materiel;
	protected Integer location;
	protected Integer exoneration;
	protected TypeMateriel type_materiel;
	protected String photo_materiel;
	protected ArrayList<Date> liste_date_reservation;
	protected Integer utilisation_reservation;


	public Materiel(){

	}

	public Materiel(Integer id, String nom, String desc, Double prix, Double caution, EtatMateriel etat, TypeMateriel type, String photo, Integer location, Integer exoneration){
		this.id_materiel = id;
		this.nom_materiel = nom;
		this.description_materiel = desc;
		this.prix_materiel = prix;
		this.caution_materiel = caution;
		this.etat_materiel = etat;
		this.type_materiel = type;
		this.photo_materiel = photo;
		this.location = location;
		this.exoneration = exoneration;
		this.utilisation_reservation = 0;
	}

	public Materiel(Integer id, String nom, String desc, Double prix, Double caution, EtatMateriel etat, TypeMateriel type){
		this.id_materiel = id;
		this.nom_materiel = nom;
		this.description_materiel = desc;
		this.prix_materiel = prix;
		this.caution_materiel = caution;
		this.etat_materiel = etat;
		this.type_materiel = type;
		this.utilisation_reservation = 0;
	}


	public Integer getUtilisation_reservation() {
		return utilisation_reservation;
	}

	public void setUtilisation_reservation(Integer utilisation_reservation) {
		this.utilisation_reservation = utilisation_reservation;
	}

	public Integer getExoneration() {
		return exoneration;
	}

	public void setExoneration(Integer exoneration) {
		this.exoneration = exoneration;
	}

	public String getPhoto_materiel() {
		return photo_materiel;
	}

	public Integer getLocation() {
		return location;
	}

	public void setLocation(Integer location) {
		this.location = location;
	}

	public void setPhoto_materiel(String photo_materiel) {
		this.photo_materiel = photo_materiel;
	}

	public Integer getId_materiel() {
		return id_materiel;
	}

	public void setId_materiel(Integer id_materiel) {
		this.id_materiel = id_materiel;
	}

	public String getNom_materiel() {
		return nom_materiel;
	}

	public void setNom_materiel(String nom_materiel) {
		this.nom_materiel = nom_materiel;
	}

	public String getDescription_materiel() {
		return description_materiel;
	}

	public void setDescription_materiel(String description_materiel) {
		this.description_materiel = description_materiel;
	}

	public Double getPrix_materiel() {
		return prix_materiel;
	}

	public void setPrix_materiel(Double prix_materiel) {
		this.prix_materiel = prix_materiel;
	}

	public Double getCaution_materiel() {
		return caution_materiel;
	}

	public void setCaution_materiel(Double caution_materiel) {
		this.caution_materiel = caution_materiel;
	}

	public EtatMateriel getEtat_materiel() {
		return etat_materiel;
	}

	public void setEtat_materiel(EtatMateriel etat_materiel) {
		this.etat_materiel = etat_materiel;
	}

	public TypeMateriel getType_materiel() {
		return type_materiel;
	}

	public void setType_materiel(TypeMateriel type_materiel) {
		this.type_materiel = type_materiel;
	}

	public ArrayList<Date> getListe_date_reservation() {
		return liste_date_reservation;
	}

	public void setListe_date_reservation(ArrayList<Date> liste_date_reservation) {
		this.liste_date_reservation = liste_date_reservation;
	}


}
