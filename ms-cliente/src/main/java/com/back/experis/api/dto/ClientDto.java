package com.back.experis.api.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.back.experis.model.Client;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ClientDto {

	@ApiModelProperty(notes = "Identificador del cliente")
	private Long clientId;
	@ApiModelProperty(notes = "Nombre del cliente")
	private String nombre;
	@ApiModelProperty(notes = "Apellido del cliente")
	private String apellido;
	@ApiModelProperty(notes = "Fecha de nacimiento")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fechaNacimiento;
	@ApiModelProperty(notes = "Fecha probable de muerte")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fechaProbableFallecido;

	public static Client toModel(ClientDto obj) {
		return new Client(obj.getNombre(), obj.getApellido(), obj.getFechaNacimiento());
	}

	public static List<ClientDto> toDto(List<Client> obj) {
		return obj.stream().map(a -> new ClientDto(a.getClientId(), a.getNombre(), a.getApellido(), a.getFechaNacimiento(),
				a.getFechaProbableFallecido())).collect(Collectors.toList());
	}

	public static ClientDto toDto(Client a) {
		return new ClientDto(a.getClientId(), a.getNombre(), a.getApellido(), a.getFechaNacimiento(), a.getFechaProbableFallecido());
	}
}
