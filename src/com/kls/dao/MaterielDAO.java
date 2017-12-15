package com.kls.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.kls.javabean.Materiel;

public interface MaterielDAO {
	void create(Materiel mat);
	Materiel find(String id);
	HashMap<Integer, Materiel> toList();
	HashMap<Integer, Materiel> toList(Date a, Date b);
	void update(Materiel mat);
	int delete(String id);
}
