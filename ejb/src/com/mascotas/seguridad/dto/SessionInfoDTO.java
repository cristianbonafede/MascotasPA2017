package com.mascotas.seguridad.dto;

public class SessionInfoDTO {
	UsuarioDTO usuario;
	String JSessionId;

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public String getJSessionId() {
		return JSessionId;
	}

	public void setJSessionId(String jSessionId) {
		this.JSessionId = jSessionId;
	}

}
