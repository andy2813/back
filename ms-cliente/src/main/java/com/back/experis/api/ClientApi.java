package com.back.experis.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.back.experis.api.dto.ClientDto;
import com.back.experis.api.dto.KpiClientDto;
import com.back.experis.service.ClientService;

import io.swagger.annotations.ApiOperation;


@RestController
public class ClientApi {
	
	@Autowired
	@Qualifier("clientService")
	private ClientService clientService;
	
	@ApiOperation(value = "Agregar un cliente",response = ClientDto.class)
	@PostMapping("/creacliente")
	public ResponseEntity<?> save(@RequestBody ClientDto obj) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ClientDto.toDto(this.clientService.save(ClientDto.toModel(obj))));
	}
	
	@ApiOperation(value = "Retorna una lista con todos los clientes",response = List.class)
	@GetMapping("/listclientes")
	public ResponseEntity<?> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(ClientDto.toDto(this.clientService.findAll()));
	}
	
	@ApiOperation(value = "Rertorna los kpi's de los clientes",response = KpiClientDto.class)
	@GetMapping("/kpideclientes")
	public ResponseEntity<?> getKpi() {
		return ResponseEntity.status(HttpStatus.OK).body(KpiClientDto.toDto(this.clientService.getKpi()));
	}
}
