package rest;


import Services.ActorServices;
import domain.Actor;
import domain.Movie;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/actors")
public class ActorRest {

    private ActorServices db = new ActorServices();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Actor> getAll() {
        return db.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Add(Actor actor){
        db.add(actor);
        return Response.ok(actor.getId()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        Actor result = db.getById(id);
        if(result == null) {
            return Response.status(404).build();
        }
        return Response.ok(result).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Actor actor) {
        Actor result = db.getById(id);
        if (result == null) {
            return Response.status(404).build();
        }
        actor.setId(id);
        db.update(actor);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        Actor result = db.getById(id);
        if (result == null) {
            return Response.status(404).build();
        }
        db.delete(result);
        return Response.ok().build();
    }

    @GET
    @Path("/{actorId}/Movies")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getComments(@PathParam("actorId") int actorId) {
        Actor result = db.getById(actorId);
        if (result == null) {
            return null;
        }
        if (result.getMovie() == null) {
            result.setMovie(new ArrayList<Movie>());
        }
        return result.getMovie();
    }

    @POST
    @Path("/{id}/Movies")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMovie(@PathParam("id") int actorId, Movie movie) {
        Actor result = db.getById(actorId);
        if (result == null) {
            return Response.status(404).build();
        }
        if (result.getMovie() == null) {
            result.setMovie(new ArrayList<Movie>());
        }

        db.addMovie(result, movie);
        return Response.ok().build();
    }


}
