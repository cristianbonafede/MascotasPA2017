package com.mascotas.concurso.dto;


import com.mascotas.concurso.entities.Concurso;


public class ActualizarConcursoDTO {

	private Integer id;
	private String descripcion;
	private Integer mascota;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	public Integer getMascota() {
		return mascota;
	}

	public void setMascota(Integer mascota) {
		this.mascota = mascota;
	}

	//Factory
	public static class Factory {
		public static ActualizarConcursoDTO get(Concurso concurso) {
			ActualizarConcursoDTO result = new ActualizarConcursoDTO();
			result.id = concurso.getId();
			result.descripcion= concurso.getDescripcion();
			if (concurso.getMascota() != null) {
				result.mascota = concurso.getMascota().getId();
			}
			return result;
		}
	}
}
