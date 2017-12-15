package com.kls.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.kls.javabean.Client;
import com.kls.javabean.Materiel;
import com.kls.javabean.Reservation;

public interface ReservationDAO {
	Reservation create(Reservation rese);
	Reservation find(Integer id);
	HashMap<Integer, Reservation> toList();
	void update(Reservation reservation, HashMap<Integer, Materiel> mat);
	ArrayList<Materiel> getMaterielReserve(Integer id);
	int delete(String id);
}
