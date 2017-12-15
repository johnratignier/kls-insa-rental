package com.kls.dao;

import java.util.ArrayList;
import java.util.Map;

import com.kls.javabean.Client;
import com.kls.javabean.DroitsMembre;
import com.kls.javabean.Materiel;
import com.kls.javabean.Statut;

public interface StatutDAO {
	void create(Statut statut);
	Statut find(String id);
	ArrayList<Statut> toList();
	void update(Statut statut);
	int delete(String id);
}
