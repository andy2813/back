package com.back.experis.repository;

import java.util.List;

import com.back.experis.model.Client;

public interface ClientRepository {
	
	public abstract List<Client> findAll();
	public abstract Client save(Client obj);
}
