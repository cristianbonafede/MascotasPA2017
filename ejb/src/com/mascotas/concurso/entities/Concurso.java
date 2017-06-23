package com.mascotas.concurso.entities;




import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.mascotas.mascotas.entities.Mascota;
import com.mascotas.seguridad.entities.Usuario;


@Entity(name = "concurso")
public class Concurso implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String descripcion;

	@OneToOne(cascade = CascadeType.REFRESH, optional = false)
	@JoinColumn(name = "login", unique = true, nullable = false, updatable = false)
	private Usuario usuario;

	@OneToOne(cascade = CascadeType.REFRESH)
	private Mascota mascota;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Mascota getMascota() {
		return mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
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
