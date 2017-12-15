package com.kls.javabean;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class Reservation implements Serializable {

	protected Integer id_reservation;
	protected Client client_reservation;
	protected Membre membre_reservation;
	protected Double total_prix_reservation;
	protected Double total_caution_reservation;
	protected Double total_prix_avant_remise_reservation;
	protected Date depart_reservation;
	protected Date retour_reservation;
	protected Integer duree_reservation;
	protected String commentaires;
	protected Facture facture_reservation;
	protected ArrayList<Materiel> list_materiel_reservation;

	public Reservation(){
	}

	public Reservation(Client client, Membre membre, Date depart, Date retour, Integer duree){
		this.client_reservation = client;
		this.membre_reservation = membre;
		this.depart_reservation = depart;
		this.retour_reservation = retour;
		this.duree_reservation = duree;
	}

	public Reservation(Client client, Membre membre, Date depart, Date retour, Integer duree, Double prix, Double caution, String com){
		this.client_reservation = client;
		this.membre_reservation = membre;
		this.depart_reservation = depart;
		this.retour_reservation = retour;
		this.duree_reservation = duree;
		this.total_prix_reservation = prix;
		this.total_caution_reservation = caution;
		this.commentaires = com;
	}


	public Facture getFacture_reservation() {
		return facture_reservation;
	}

	public void setFacture_reservation(Facture facture_reservation) {
		this.facture_reservation = facture_reservation;
	}

	public Double getTotal_prix_avant_remise_reservation() {
		return total_prix_avant_remise_reservation;
	}

	public void setTotal_prix_avant_remise_reservation(Double total_prix_avant_remise_reservation) {
		this.total_prix_avant_remise_reservation = total_prix_avant_remise_reservation;
	}

	public String getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(String commentaires) {
		this.commentaires = commentaires;
	}

	public Integer getId_reservation() {
		return id_reservation;
	}

	public ArrayList<Materiel> getList_materiel_reservation() {
		return list_materiel_reservation;
	}

	public void setList_materiel_reservation(ArrayList<Materiel> list_materiel_reservation) {
		this.list_materiel_reservation = list_materiel_reservation;
	}

	public void setId_reservation(Integer id_reservation) {
		this.id_reservation = id_reservation;
	}

	public Client getClient_reservation() {
		return client_reservation;
	}

	public void setClient_reservation(Client client_reservation) {
		this.client_reservation = client_reservation;
	}

	public Membre getMembre_reservation() {
		return membre_reservation;
	}

	public void setMembre_reservation(Membre membre_reservation) {
		this.membre_reservation = membre_reservation;
	}

	public Double getTotal_prix_reservation() {
		return total_prix_reservation;
	}

	public void setTotal_prix_reservation(Double total_prix_reservation) {
		this.total_prix_reservation = total_prix_reservation;
	}

	public Double getTotal_caution_reservation() {
		return total_caution_reservation;
	}

	public void setTotal_caution_reservation(Double total_caution_reservation) {
		this.total_caution_reservation = total_caution_reservation;
	}

	public Date getDepart_reservation() {
		return depart_reservation;
	}

	public void setDepart_reservation(Date depart_reservation) {
		this.depart_reservation = depart_reservation;
	}

	public Date getRetour_reservation() {
		return retour_reservation;
	}

	public void setRetour_reservation(Date retour_reservation) {
		this.retour_reservation = retour_reservation;
	}

	public Integer getDuree_reservation() {
		return duree_reservation;
	}

	public void setDuree_reservation(Integer duree_reservation) {
		this.duree_reservation = duree_reservation;
	}



}
