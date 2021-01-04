
/**
 * Write a description of SecondRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.ArrayList;

public class ThirdRatings {
    private ArrayList<EfficientRater> myRaters;

    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }

    public ThirdRatings(String ratingFile) {
        FirstRatings firstRatings = new FirstRatings();
        myRaters = firstRatings.loadRaters(ratingFile);
    }



    public int getRaterSize() {
        return myRaters.size();
    }

    private double getAverageByID(String movieId, int minimalRaterNum) {
        int numOfRatings = 0;
        double totalRatings = 0;
        for (EfficientRater rater : myRaters) {
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
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> movieIds = MovieDatabase.filterBy(new TrueFilter());
        for (String id : movieIds) {
            double avgRating = getAverageByID(id, minimalRaterNum);
            Rating rating = new Rating(id, avgRating);
            ratingList.add(rating);
        }
        return ratingList;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> ratingList = new ArrayList<Rating>();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> movieId = MovieDatabase.filterBy(filterCriteria);
        for (String id : movieId) {
            double avgRating = getAverageByID(id, minimalRaters);
            Rating rating = new Rating(id, avgRating);
            ratingList.add(rating);
        }
        return ratingList;
    }
}