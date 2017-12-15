package com.kls.dao;

import java.util.ArrayList;
import java.util.Map;

import com.kls.javabean.Client;
import com.kls.javabean.DroitsMembre;
import com.kls.javabean.Facture;
import com.kls.javabean.Materiel;
import com.kls.javabean.Statut;

public interface FactureDAO {
	int create(String facture);
	Facture find(String id);
	void update(Facture statut);
	int delete(String id);
}
