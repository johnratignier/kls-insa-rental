package com.kls.javabean;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class Membre implements Serializable {

	protected String prenom;
	protected String nom;
	protected String adresse_mail;
	protected String numero_tel;
	protected Integer id_user;
	protected String departement_etude;
	protected String departement_annee;
	protected String pseudo_artiste;
	protected Date date_naissance;
	protected String photo_profil;
	protected Statut  statut;
	protected DroitsMembre droits;
	protected String mot_de_passe;
	protected ArrayList<Reservation> liste_reservation;

	public Membre(){
		this.id_user = -1;
		this.prenom = "undefined_prenom";
		this.nom = "undefined_nom";
		this.adresse_mail = "undefined_mail";
		this.numero_tel = "undefined_tel";
		this.date_naissance = new Date(0);
		this.photo_profil = "undefinel_photo";
		this.droits  = new DroitsMembre();
		this.mot_de_passe = "undefined_mdp";	
		this.liste_reservation = new ArrayList<Reservation>();
	}
	
	public Membre(Integer id){
		this.id_user = id;
	}
	
	public Membre(Integer id, String prenom, String nom, String mail, String tel, String depet, String depan, String pseudo, Date date, String photo, Statut statut, DroitsMembre droits){
		this.id_user = id;
		this.prenom = prenom;
		this.nom = nom;
		this.adresse_mail = mail;
		this.numero_tel = tel;
		this.departement_etude = depet;
		this.departement_annee = depan;
		this.pseudo_artiste = pseudo;
		this.date_naissance = date;
		this.photo_profil = photo;
		this.statut = statut;
		this.droits = droits;
	}


	public String getPseudo_artiste() {
		return pseudo_artiste;
	}


	public void setPseudo_artiste(String pseudo_artiste) {
		this.pseudo_artiste = pseudo_artiste;
	}


	public String getDepartement_etude() {
		return departement_etude;
	}

	public void setDepartement_etude(String departement_etude) {
		this.departement_etude = departement_etude;
	}

	public String getDepartement_annee() {
		return departement_annee;
	}

	public void setDepartement_annee(String departement_annee) {
		this.departement_annee = departement_annee;
	}

	public ArrayList<Reservation> getListe_reservation() {
		return liste_reservation;
	}

	public void setListe_reservation(ArrayList<Reservation> liste_reservation) {
		this.liste_reservation = liste_reservation;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse_mail() {
		return adresse_mail;
	}

	public void setAdresse_mail(String adresse_mail) {
		this.adresse_mail = adresse_mail;
	}

	public String getNumero_tel() {
		return numero_tel;
	}

	public void setNumero_tel(String numero_tel) {
		this.numero_tel = numero_tel;
	}

	public Integer getId_user() {
		return id_user;
	}

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}

	public Date getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}

	public String getPhoto_profil() {
		return photo_profil;
	}

	public void setPhoto_profil(String photo_profil) {
		this.photo_profil = photo_profil;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public DroitsMembre getDroits() {
		return droits;
	}

	public void setDroits(DroitsMembre droits) {
		this.droits = droits;
	}

	public String getMot_de_passe() {
		return mot_de_passe;
	}

	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}



}
