package Services;

import domain.Actor;
import domain.Comment;
import domain.Movie;
import domain.Rate;

import java.util.ArrayList;
import java.util.List;

public class MovieServices {

    private static List<Movie> db = new ArrayList<Movie>();
    private static int currentId = -1;

    public List<Movie> getAll() {
        return db;
    }

    public Movie getById(int id) {
        for (Movie movie : db) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    public void add (Movie movie) {
        movie.setId(++currentId);
        db.add(movie);
    }

    public void update (Movie movie) {
        for (Movie m : db) {
            if (m.getId() == movie.getId()) {
                m.setTitle(movie.getTitle());
                m.setYear(movie.getYear());
            }
        }
    }

    public void delete (Movie movie) {
        db.remove(movie);
    }

    public void addComment (Movie movie, Comment comment) {
        comment.setId(movie.getComment().size());
        movie.getComment().add(comment);
    }

    public void deleteComment (Movie movie, int commentId) {
        db.get(movie.getId()).getComment().remove(commentId);
    }

    public void addRate(Movie movie, Rate rating) {
        rating.setId(movie.getRate().size());
        movie.getRate().add(rating);

        double sum = 0;
        for (Rate r: movie.getRate()) {
            sum = sum + r.getRating();
        }

        double movieRating = sum/movie.getRate().size();

        movie.setMovieRating(movieRating);
    }

    public List<Actor> addActor(int movieId) {
        MovieServices db = new MovieServices();
        List<Actor> actorsForMovie = new ArrayList<Actor>();
        List<Actor> actorsDb = new ActorServices().getAll();

        for (Movie movie : db.getAll()) {
            if (movie.getId() == movieId) {
                for (Integer actorId : movie.getActors()) {
                    for (Actor actor : actorsDb) {
                        if (actor.getId() == actorId) {
                            actorsForMovie.add(actor);
                        }
                    }
                }
            }
        }

        return actorsForMovie;
    }
}
