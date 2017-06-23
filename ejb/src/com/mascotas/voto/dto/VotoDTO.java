package com.mascotas.voto.dto;




import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mascotas.concurso.dto.ConcursoDTO;
import com.mascotas.voto.entities.Voto;



public class VotoDTO implements Serializable {


	private static final long serialVersionUID = 1L;

	private Integer id;

	private ConcursoDTO concurso;


	public Integer getId() {
		return id;
	}


	public ConcursoDTO getConcurso() {
		return concurso;
	}




	//Factorys
	public static class Factory {
		public static VotoDTO get(Voto voto) {
			VotoDTO result = new VotoDTO();
			result.id = voto.getId();
			result.concurso = ConcursoDTO.Factory.get(voto.getConcurso());
			return result;
		}

		public static List<VotoDTO> get(Collection<Voto> votos) {
			ArrayList<VotoDTO> result = new ArrayList<VotoDTO>();
			for (Voto p : votos) {
				result.add(get(p));
			}
			return result;
		}

	}

}
