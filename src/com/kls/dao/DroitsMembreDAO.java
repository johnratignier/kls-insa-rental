package com.kls.dao;

import java.util.ArrayList;
import java.util.Map;

import com.kls.javabean.Client;
import com.kls.javabean.DroitsMembre;
import com.kls.javabean.Materiel;

public interface DroitsMembreDAO {
	void create(DroitsMembre droits);
	DroitsMembre find(String id);
	ArrayList<DroitsMembre> toList();
	void update(DroitsMembre client);
	int delete(String id);
}
