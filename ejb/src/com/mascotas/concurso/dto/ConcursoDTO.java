package com.mascotas.concurso.dto;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mascotas.concurso.entities.Concurso;
import com.mascotas.mascotas.dto.MascotaDTO;


public class ConcursoDTO implements Serializable {


	private static final long serialVersionUID = 1L;

	private Integer id;

	private String descripcion;

	private MascotaDTO mascota;


	public Integer getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}


	public MascotaDTO getMascota() {
		return mascota;
	}


//Factorys
	public static class Factory {
		public static ConcursoDTO get(Concurso concurso) {
			ConcursoDTO result = new ConcursoDTO();
			result.id = concurso.getId();
			result.descripcion = concurso.getDescripcion();
			result.mascota = MascotaDTO.Factory.get(concurso.getMascota());
			return result;
		}

		public static List<ConcursoDTO> get(Collection<Concurso> concursos) {
			ArrayList<ConcursoDTO> result = new ArrayList<ConcursoDTO>();
			for (Concurso p : concursos) {
				result.add(get(p));
			}
			return result;
		}

	}

}
