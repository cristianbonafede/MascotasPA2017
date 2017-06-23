package com.mascotas.voto.entities;




import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import com.mascotas.concurso.entities.Concurso;
import com.mascotas.seguridad.entities.Usuario;


@Entity(name = "voto")
public class Voto implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	@ManyToOne(cascade = CascadeType.REFRESH)
	private Concurso concurso;

	@ManyToOne(cascade = CascadeType.REFRESH)
	private Usuario usuario;


	public Concurso getConcurso() {
		return concurso;
	}

	public void setConcurso(Concurso concurso) {
		this.concurso = concurso;
	}

	public Integer getId() {
		return id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}