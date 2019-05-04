package rest;

import Services.MovieServices;
import domain.Actor;
import domain.Comment;
import domain.Movie;
import domain.Rate;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/movies")
public class MovieRest {

    private MovieServices db = new MovieServices();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getAll() {
        return db.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Add(Movie movie) {
        db.add(movie);
        return Response.ok(movie.getId()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        Movie result = db.getById(id);
        if (result == null) {
            return Response.status(404).build();
        }
        return Response.ok(result).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Movie movie) {
        Movie result = db.getById(id);
        if (result == null) {
            return Response.status(404).build();
        }
        movie.setId(id);
        db.update(movie);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        Movie result = db.getById(id);
        if (result == null) {
            return Response.status(404).build();
        }
        db.delete(result);
        return Response.ok().build();
    }

    @GET
    @Path("/{movieId}/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getComments(@PathParam("movieId") int movieId) {
        Movie result = db.getById(movieId);
        if (result == null) {
            return null;
        }
        if (result.getComment() == null) {
            result.setComments(new ArrayList<Comment>());
        }
        return result.getComment();
    }

    @POST
    @Path("/{id}/comments")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addComment(@PathParam("id") int movieId, Comment comment) {
        Movie result = db.getById(movieId);
        if (result == null) {
            return Response.status(404).build();
        }
        if (result.getComment() == null) {
            result.setComments(new ArrayList<Comment>());
        }

        db.addComment(result, comment);

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}/comments/{commentId}")
    public Response deleteComment(@PathParam("id") int movieId, @PathParam("commentId") int commentId) {
        Movie result = db.getById(movieId);
        if (result == null) {
            return Response.status(404).build();
        }
        db.deleteComment(result, commentId);
        return Response.ok().build();
    }

    @GET
    @Path("/{movieId}/rate")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rate> getRatings(@PathParam("movieId") int movieId) {
        Movie result = db.getById(movieId);
        if (result == null) {
            return null;
        }
        if (result.getRate() == null) {
            result.setRate(new ArrayList<Rate>());
        }
        return result.getRate();
    }

    @POST
    @Path("/{id}/rate")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRatings(@PathParam("id") int movieId, Rate rating) {
        Movie result = db.getById(movieId);
        if (result == null) {
            return Response.status(404).build();
        }
        if (result.getRate() == null) {
            result.setRate(new ArrayList<Rate>());
        }
        db.addRate(result, rating);
        return Response.ok().build();
    }

    @GET
    @Path("/{movieId}/actors")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Actor> getActors(@PathParam("movieId") int movieId) {
        Movie result = db.getById(movieId);
        if (result == null) {
            return null;
        }

        return db.addActor(movieId);
    }
}
