package com.kls.dao;

import java.util.HashMap;
import java.util.Map;

import com.kls.javabean.Materiel;
import com.kls.javabean.Membre;

public interface MembreDAO {
	void create(Membre user);
	HashMap<Integer, Membre> toList();
	Membre find(String mail);
	Membre find(Integer id);
	void update(Membre mb, Integer id);
	void updateEx(Membre mb);
	void updateMDP(Integer id, String mdp);
	int delete(String id);
}
