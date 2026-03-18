package com.robson.resource;

import com.robson.dto.PagedResponseDto;
import com.robson.dto.UserRequestDto;
import com.robson.dto.UserResponseDto;
import com.robson.service.UserService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;

    @GET
    public PagedResponseDto<UserResponseDto> findAll(
            @QueryParam("name") String name,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("5") int size
    ) {
        return userService.findAll(name, page, size);
    }

    @GET
    @Path("/{id}")
    public UserResponseDto findById(@PathParam("id") Long id) {
        return userService.findById(id);
    }

    @POST
    public Response create(@Valid UserRequestDto dto) {
        UserResponseDto createdUser = userService.create(dto);
        return Response.status(Response.Status.CREATED).entity(createdUser).build();
    }

    @PUT
    @Path("/{id}")
    public UserResponseDto update(@PathParam("id") Long id, @Valid UserRequestDto dto) {
        return userService.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        userService.delete(id);
        return Response.noContent().build();
    }
}