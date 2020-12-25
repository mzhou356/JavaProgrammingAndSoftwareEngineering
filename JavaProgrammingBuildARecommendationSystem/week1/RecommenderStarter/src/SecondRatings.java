
/**
 * Write a description of SecondRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    public SecondRatings(String movieFile, String ratingFile) {
        FirstRatings firstRatings = new FirstRatings();
        myMovies = firstRatings.loadMovies(movieFile);
        myRaters = firstRatings.loadRaters(ratingFile);
    }

    public int getMovieSize() {
        return myMovies.size();
    }

    public int getRaterSize() {
        return myRaters.size();
    }

    private double getAverageByID(String movieId, int minimalRaterNum) {
        int numOfRatings = 0;
        double totalRatings = 0;
        for (Rater rater : myRaters) {
            double rating = rater.getRating(movieId);
            if (rating != -1) {
                totalRatings += rating;
                numOfRatings++;
            }
        }
        if (numOfRatings < minimalRaterNum) {
            return 0.0;
        } else {
            return totalRatings / numOfRatings;
        }
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaterNum) {
        ArrayList<Rating> ratingList = new ArrayList<Rating>();
        for (Movie movie : myMovies) {
            String movieId = movie.getID();
            double avgRating = getAverageByID(movieId, minimalRaterNum);
            Rating rating = new Rating(movieId, avgRating);
            ratingList.add(rating);
        }
        return ratingList;
    }

    public String getTitle(String movieID) {
        String title = "This movieID is not found";
        for (Movie movie : myMovies) {
            String id = movie.getID();
            if (id.equals(movieID)) {
                title = movie.getTitle();
                break;
            }
        }
        return title;
    }

    public String getID(String title){
        String id = "This movie title is not found";
        for (Movie movie:myMovies){
            String movieTitle = movie.getTitle();
            if (movieTitle.equals(title)){
                id = movie.getID();
                break;
            }
        }
        return id;
    }
}