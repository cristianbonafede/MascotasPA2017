package com.mascotas.provincias.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Provincias en el sistema
 * 
 * @author nestor
 */
@Entity(name = "provincia")
public class Provincia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nombre;

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

}
