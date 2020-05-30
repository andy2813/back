package com.back.experis.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.back.experis.model.Client;
import com.back.experis.repository.ClientRepository;


@Repository("clientRepository")
public class ClientRepositoryImpl implements ClientRepository{

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private final String SQL_FIND_ALL = "SELECT ID, NOMBRE, APELLIDO, FECHA_NACIMIENTO FROM bd_app.CLIENTE";

	private final String SQL_INSERT = "INSERT INTO bd_app.CLIENTE (NOMBRE, APELLIDO, FECHA_NACIMIENTO) "
			+ " VALUES (:nombre, :apellido, :fechaNacimiento)";

	public List<Client> findAll() {
		return this.namedParameterJdbcTemplate.query(SQL_FIND_ALL, (rs, rowNum) -> this.toReturn(rs));
	}
	
	public Client save(Client obj) {
		KeyHolder holder = new GeneratedKeyHolder();

		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("nombre", obj.getNombre())
				.addValue("apellido", obj.getApellido())
				.addValue("fechaNacimiento", obj.getFechaNacimiento());

		this.namedParameterJdbcTemplate.update(SQL_INSERT, parameters, holder);
		obj.setClientId(holder.getKey().longValue());
		return obj;
	}
	
	private Client toReturn(ResultSet rs) throws SQLException {
		return new Client(rs.getLong("ID"), rs.getString("NOMBRE"), rs.getString("APELLIDO"), 
				rs.getDate("FECHA_NACIMIENTO"));
	}
}
