package com.back.experis.service;

import java.util.List;

import com.back.experis.model.Client;
import com.back.experis.model.KpiClient;

public interface ClientService {

	public abstract List<Client> findAll();
	public abstract Client save(Client obj);
	public abstract KpiClient getKpi();
}
