package com.kls.dao;

import java.util.ArrayList;

import com.kls.javabean.EtatMateriel;
import com.kls.javabean.TypeMateriel;

public interface EtatMaterielDAO {
	void create(EtatMateriel em);
	ArrayList<EtatMateriel> toList();
	void update(EtatMateriel em);
	int delete(String id);
}
