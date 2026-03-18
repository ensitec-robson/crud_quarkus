package com.robson.filter;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.USER)
public class RequestLoggingFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String method = requestContext.getMethod();
        String path = requestContext.getUriInfo().getPath();
        String query = requestContext.getUriInfo().getRequestUri().getQuery();

        if (query != null && !query.isBlank()) {
            System.out.println("[LOG]" + method + " /" + path + "?" + query);
        } else {
            System.out.println("[LOG] " + method + " /" + path);
        }
    }
}