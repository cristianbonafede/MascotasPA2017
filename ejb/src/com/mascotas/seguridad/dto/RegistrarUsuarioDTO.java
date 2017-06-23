package com.mascotas.seguridad.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.mascotas.seguridad.entities.Usuario;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrarUsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String login;
	private String password;
	private String nombre;
	private String email;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public static final class Factory {
		/**
		 * Retorna un RegistrarUsuarioDTO populado con toda la informacion
		 * posible.
		 * 
		 * @param u
		 * @return
		 */
		public static RegistrarUsuarioDTO get(Usuario u) {
			if (u == null) {
				return null;
			}
			RegistrarUsuarioDTO result = new RegistrarUsuarioDTO();
			result.setLogin(u.getLogin());
			result.setEmail(u.getEmail());
			result.setNombre(u.getNombre());

			return result;
		}
	}
}
