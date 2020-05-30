package com.back.experis.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.back.experis.model.Client;
import com.back.experis.model.KpiClient;
import com.back.experis.repository.ClientRepository;
import com.back.experis.service.ClientService;

@Service("clientService")
public class ClientServiceImpl implements ClientService {

	@Autowired
	@Qualifier("clientRepository")
	private ClientRepository clientRepository;

	public List<Client> findAll() {

		List<Client> lst = this.clientRepository.findAll();

		return lst.stream()
				.map(a -> new Client(a.getClientId(), a.getNombre(), a.getApellido(), a.getFechaNacimiento(),
						this.fechaDecesoProbable(a.getFechaNacimiento(), this.calcular(lst).getDesviacion())))
				.collect(Collectors.toList());

	}

	public Client save(Client obj) {
		return this.clientRepository.save(obj);
	}

	public KpiClient getKpi() {
		return this.calcular(this.clientRepository.findAll());
	}

	private Integer getEdad(Date fechaNacimiento) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaNacimiento);

		return Period.between(calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now())
				.getYears();

	}

	private KpiClient calcular(List<Client> lst) {
		
		if(lst.isEmpty()) {
			return new KpiClient(0.0, 0.0);
		}

		Double sum = lst.stream().mapToDouble(o -> this.getEdad(o.getFechaNacimiento())).sum();

		Double media = sum / lst.size();

		Double var = lst.stream().mapToDouble(o -> Math.pow((this.getEdad(o.getFechaNacimiento()) - media), 2)).sum();

		return new KpiClient(media, Math.sqrt(var / lst.size()));
	}

	private Date fechaDecesoProbable(Date fechaNacimiento, Double variacion) {

		Integer addMess = (int) Math.round((variacion * 12) / 1);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaNacimiento);

		calendar.add(Calendar.MONTH, addMess);

		return calendar.getTime();
	}

}
