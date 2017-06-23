package com.mascotas.mascotas.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.mascotas.application.utils.StringUtils;
import com.mascotas.mascotas.entities.Mascota;

/**
 * Mascotas
 * 
 * @author nestor
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MascotaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nombre;
	private String fechaNacimiento;
	private String descripcion;
	private String imagen;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	
	public String getImagen() {//quizaz tenga que devolver foram
		return imagen;
	}
	
	public void setImagen (String imagen){
		this.imagen = imagen;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Factorys para crear DTO a partir de Entity
	 * 
	 * @author nestor
	 *
	 */
	public static class Factory {
		public static MascotaDTO get(Mascota mascota) {
			MascotaDTO result = new MascotaDTO();
			result.id = mascota.getId();
			result.nombre = mascota.getNombre();
			if (mascota.getFechaNacimiento() != null) {
				result.fechaNacimiento = StringUtils.DATE_FORMAT.format(mascota.getFechaNacimiento());
			}
			result.descripcion = mascota.getDescripcion();
			result.imagen = mascota.getImagen();
			return result;
		}

		public static List<MascotaDTO> get(Collection<Mascota> mascotas) {
			ArrayList<MascotaDTO> result = new ArrayList<MascotaDTO>();
			for (Mascota p : mascotas) {
				result.add(get(p));
			}
			return result;
		}

	}

}
