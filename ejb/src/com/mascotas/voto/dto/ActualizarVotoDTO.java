package com.mascotas.voto.dto;


import com.mascotas.voto.entities.Voto;

public class ActualizarVotoDTO {
	
	private Integer id;
	private Integer concurso;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getConcurso() {
		return concurso;
	}

	public void setConcurso(Integer concurso) {
		this.concurso = concurso;
	}



	//Factorys
	public static class Factory {
		public static ActualizarVotoDTO get(Voto voto) {
			ActualizarVotoDTO result = new ActualizarVotoDTO();
			result.id = voto.getId();
			if (voto.getConcurso() != null) {
				result.concurso = voto.getConcurso().getId();
			}
			return result;
		}
	}
}
