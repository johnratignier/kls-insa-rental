package com.kls.dao;

import java.util.HashMap;
import java.util.Map;

import com.kls.javabean.Client;
import com.kls.javabean.Materiel;

public interface ClientDAO {
	void create(Client client);
	Client find(String id);
	HashMap<Integer, Client> toList();
	void update(Client client);
	int delete(String id);
}
