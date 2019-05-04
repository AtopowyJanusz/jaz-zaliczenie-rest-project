package Services;

import domain.Actor;
import domain.Movie;

import java.util.ArrayList;
import java.util.List;

public class ActorServices {
    private static List<Actor> db = new ArrayList<Actor>();
    private static int currentId = -1;

    public List<Actor> getAll() {
        return db;
    }

    public Actor getById(int id) {
        for (Actor actor : db) {
            if (actor.getId() == id) {
                return actor;
            }
        }
        return null;
    }

    public void add (Actor actor) {
        actor.setId(++currentId);
        db.add(actor);
    }

    public void update (Actor actor) {
        for (Actor a : db) {
            if (a.getId() == actor.getId()) {
                a.setName(actor.getName());
                a.setSurname(actor.getSurname());
            }
        }
    }

    public void delete (Actor actor) {
        db.remove(actor);
    }

    public void addMovie (Actor actor, Movie movie) {

        MovieServices dbMovies = new MovieServices();

        for (Movie m : dbMovies.getAll()) {
            if (m.getId() == movie.getId()) {
                actor.getMovie().add(m);

                if (m.getActors() == null) {
                    m.setActors(new ArrayList<Integer>());
                }

                m.getActors().add(actor.getId());
            }
        }
    }
}
