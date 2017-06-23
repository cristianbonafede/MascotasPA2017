package com.mascotas.application.dtos;

import java.util.ArrayList;
import java.util.List;

import com.mascotas.application.exceptions.BusinessException;
import com.mascotas.application.exceptions.ValidationError;

public class FormError {
	List<Error> message = new ArrayList<FormError.Error>();
	String error;

	public void addMessage(String path, String text) {
		message.add(new Error(path, text));
	}

	public List<Error> getMessage() {
		return message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	static class Error {
		String path;
		String message;

		public Error(String path2, String text) {
			this.path = path2;
			this.message = text;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}

	/**
	 * Procesa el BusinessException para generar la salida correspondiente.
	 * 
	 * @param e
	 * @return
	 */
	public static FormError processError(BusinessException e) {
		FormError result = new FormError();
		for (ValidationError error : e.getErrores()) {
			result.addMessage(error.getProperty(), error.getError());
		}
		return result;
	}

	/**
	 * Procesa la salida de cualquier otro error.
	 * 
	 * @param e
	 * @return
	 */
	public static FormError processError(Exception e) {
		FormError result = new FormError();
		if (e != null) {
			result.setError(e.getMessage());
		} else {
			result.setError("{}");
		}
		return result;
	}

}