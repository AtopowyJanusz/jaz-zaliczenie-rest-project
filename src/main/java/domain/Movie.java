package domain;

import java.util.List;

public class Movie {

    private int id;
    private String title;
    private String year;
    private double movieRating;
    private List<Rate> ratings;
    private List<Integer> actors;
    private List<Comment> comments;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public double getMovieRating() {
        return movieRating;
    }
    public void setMovieRating(double movieRating) {
        this.movieRating = movieRating;
    }
    public List<Rate> getRate() {
        return ratings;
    }
    public void setRate(List<Rate> rate) {
        this.ratings = rate;
    }
    public List<Integer> getActors() {
        return actors;
    }
    public void setActors(List<Integer> actors) {
        this.actors = actors;
    }
    public List<Comment> getComment() {
        return comments;
    }
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
