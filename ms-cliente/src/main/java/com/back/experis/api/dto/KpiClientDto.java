package com.back.experis.api.dto;

import com.back.experis.model.KpiClient;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class KpiClientDto {

	@ApiModelProperty(notes = "Promedio de edad entre todos los clientes")
	private Double promedioEdad;
	@ApiModelProperty(notes = "Desvicación estándar entre las edades de todos los clientes")
	private Double desviacion;
	
	public static KpiClientDto toDto (KpiClient obj) {
		return new KpiClientDto(obj.getPromedioEdad(), obj.getDesviacion());
	}
}
