package com.kls.manage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.kls.dao.ClientDAO;
import com.kls.dao.MaterielDAO;
import com.kls.dao.ReservationDAO;
import com.kls.javabean.Client;
import com.kls.javabean.EtatMateriel;
import com.kls.javabean.Materiel;
import com.kls.javabean.Membre;
import com.kls.javabean.Reservation;
import com.kls.javabean.TypeMateriel;

public class ReservationManage {

	private MaterielDAO materielDAO;
	private ClientDAO clientDAO;
	private ReservationDAO reservationDAO;

	public ReservationManage(ReservationDAO m){
		this.reservationDAO = m;
	}


	public void updateReservation(Reservation reservation, HashMap<Integer, Materiel> materiel){
		Double prix = 0.0;
		Double caution = 0.0;
		Double prix_avant_remise = 0.0;
		Double remise = reservation.getClient_reservation().getType_client().getTaux_remise_client();
		for(HashMap.Entry<Integer, Materiel> entry : materiel.entrySet()){
			if(entry.getValue().getExoneration()==1){
				prix+=entry.getValue().getPrix_materiel()*(1.0-0.01*remise)*reservation.getDuree_reservation();
			}else{
				prix+=entry.getValue().getPrix_materiel()*reservation.getDuree_reservation();
			}
			prix_avant_remise += entry.getValue().getPrix_materiel()*reservation.getDuree_reservation();
			caution+=entry.getValue().getCaution_materiel();
		}
		reservation.setTotal_caution_reservation(caution);
		reservation.setTotal_prix_reservation(prix);
		reservation.setTotal_prix_avant_remise_reservation(prix_avant_remise);
		
		this.reservationDAO.update(reservation, materiel);
	}


}
