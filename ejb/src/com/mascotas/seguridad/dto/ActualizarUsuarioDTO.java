package com.mascotas.seguridad.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mascotas.seguridad.entities.Usuario;

public class ActualizarUsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String login;
	private List<String> roles;
	private String nombre;
	private String email;
	private Date inicioVigencia;

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

	public Date getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	public static final class Factory {
		/**
		 * Retorna un ActualizarUsuarioDTO populado con toda la informacion
		 * posible.
		 * 
		 * @param u
		 * @return
		 */
		public static ActualizarUsuarioDTO get(Usuario u) {
			if (u == null) {
				return null;
			}
			ActualizarUsuarioDTO result = new ActualizarUsuarioDTO();
			result.setLogin(u.getLogin());
			result.setEmail(u.getEmail());
			result.setInicioVigencia(u.getInicioVigencia());
			result.setNombre(u.getNombre());

			for (RolSeguridad r : u.getRoles()) {
				result.getRoles().add(r.toString());
			}

			return result;
		}
	}
}
