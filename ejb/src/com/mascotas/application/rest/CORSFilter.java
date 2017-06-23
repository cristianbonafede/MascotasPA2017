package com.mascotas.application.rest;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class CORSFilter implements ContainerResponseFilter {

	@Override
	public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext cres)
			throws IOException {
		if (requestContext.getHeaders() != null && requestContext.getHeaders().get("Origin") != null
				&& requestContext.getHeaders().get("Origin").size() > 0) {
			cres.getHeaders().add("Access-Control-Allow-Origin",
					requestContext.getHeaders().get("Origin").get(0).toString());
			cres.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
			cres.getHeaders().add("Access-Control-Allow-Credentials", "true");
			cres.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
			cres.getHeaders().add("Access-Control-Max-Age", "1209600");
		}
	}

}