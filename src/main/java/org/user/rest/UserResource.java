package org.user.rest;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.user.entity.User;

import java.util.HashMap;
import java.util.Map;

@Path("/users")
public class UserResource {

    public static final String ERROR = "error";

    private static final Map<String, User> users = new HashMap<>();

    static {
        users.put("U001", new User("U001", "Pooja", true));
        users.put("U002", new User("U002", "Ankita", false)); // Inactive
        users.put("U003", new User("U003", "Anu", true));
    }

    // http://localhost:8081/users/validate/
    @GET
    @Path("/validate/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validateUser(@PathParam("userId") String userId) {
        User user = users.get(userId);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of(ERROR , "User not found"))
                    .build();
        }

        if (!user.isActive()) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity(Map.of(ERROR, "User is inactive"))
                    .build();
        }

        return Response.ok(Map.of(
                "userId", user.getUserId(),
                "name", user.getName(),
                "active", user.isActive()
        )).build();
    }
}

