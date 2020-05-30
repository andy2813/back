package com.back.experis.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Client {
	private Long clientId;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private Date fechaProbableFallecido;

	public Client(String nombre, String apellido, Date fechaNacimiento) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
	}

	public Client(Long clientId, String nombre, String apellido, Date fechaNacimiento) {
		super();
		this.clientId = clientId;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
	}

}
