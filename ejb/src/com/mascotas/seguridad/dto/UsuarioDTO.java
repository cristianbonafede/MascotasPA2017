package com.mascotas.seguridad.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mascotas.seguridad.entities.Usuario;

public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String login;
	private List<String> roles;
	private String nombre;
	private String email;
	private Date finVigencia;
	private Date inicioVigencia;
	private boolean activo;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<String> getRoles() {
		if (roles == null) {
			roles = new ArrayList<String>();
		}
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFinVigencia() {
		return finVigencia;
	}

	public void setFinVigencia(Date finVigencia) {
		this.finVigencia = finVigencia;
	}

	public Date getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public static final class Factory {
		/**
		 * Retorna un UsuarioDTO populado con toda la informacion posible.
		 * 
		 * @param u
		 * @return
		 */
		public static UsuarioDTO get(Usuario u) {
			if (u == null) {
				return null;
			}
			UsuarioDTO result = new UsuarioDTO();
			result.setLogin(u.getLogin());
			result.setEmail(u.getEmail());
			result.setFinVigencia(u.getFinVigencia());
			result.setInicioVigencia(u.getInicioVigencia());
			result.setNombre(u.getNombre());
			result.setActivo((u.getInicioVigencia() == null || u.getInicioVigencia().before(new java.util.Date()) && (u.getFinVigencia() == null || u.getFinVigencia().after(new java.util.Date()))));

			for (RolSeguridad r : u.getRoles()) {
				result.getRoles().add(r.toString());
			}

			return result;
		}
	}
}
