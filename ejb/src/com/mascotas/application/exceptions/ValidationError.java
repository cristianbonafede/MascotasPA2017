package com.mascotas.application.exceptions;

import java.io.Serializable;

/**
 * Esta clase permite representar un error de validacion en un dto o entidad del
 * negocio.
 * 
 * @author Nestor
 *
 */
public class ValidationError implements Serializable {
	private static final long serialVersionUID = 1L;

	// Propiedad donde se produce el error
	private String property;

	// Mensaje de error.
	private String error;

	public ValidationError(String property, String error) {
		this.property = property;
		this.error = error;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
