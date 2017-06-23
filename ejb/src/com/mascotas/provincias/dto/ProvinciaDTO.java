package com.mascotas.provincias.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mascotas.provincias.entites.Provincia;

/**
 * Objeto de transferencia de Datos.
 * 
 * @author nestor
 */
public class ProvinciaDTO {
	private Integer id;
	private String nombre;

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	/**
	 * Factory de creacion de ProvinciaDTO
	 * 
	 * @author nestor
	 *
	 */
	public static class Factory {
		public static ProvinciaDTO get(Provincia provincia) {
			ProvinciaDTO result = new ProvinciaDTO();
			result.id = provincia.getId();
			result.nombre = provincia.getNombre();
			return result;
		}

		public static List<ProvinciaDTO> get(Collection<Provincia> provincias) {
			ArrayList<ProvinciaDTO> result = new ArrayList<ProvinciaDTO>();
			for (Provincia p : provincias) {
				result.add(get(p));
			}
			return result;
		}
	}

}
