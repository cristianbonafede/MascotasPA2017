package com.mascotas.mascotas.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Basic;	   
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import javax.persistence.FetchType;

import com.mascotas.seguridad.entities.Usuario;

/**
 * Mascotas
 * 
 * @author nestor
 *
 */
@Entity(name = "mascotas")
public class Mascota implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OrderColumn()
	@Column(nullable = false)
	private String nombre;

	@Temporal(TemporalType.DATE)
	private java.util.Date fechaNacimiento;

	@Lob @Basic(fetch= FetchType.EAGER)
	private byte[] imagen; 

	private String descripcion;

	@ManyToOne(cascade = CascadeType.REFRESH)
	private Usuario usuario;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public java.util.Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(java.util.Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
 
	public String getImagen() {
		String b = new String(this.imagen);   
		      return b;
	}
	
	   public void setImagen(String imagen) {

		      this.imagen =  imagen.getBytes();
		   }


	   
}
