package com.kls.dao;

import java.util.ArrayList;

import com.kls.javabean.TypeMateriel;

public interface TypeMaterielDAO {
	void create(TypeMateriel tm);
	ArrayList<TypeMateriel> toList();
	void update(TypeMateriel tm);
	int delete(String id);
}
