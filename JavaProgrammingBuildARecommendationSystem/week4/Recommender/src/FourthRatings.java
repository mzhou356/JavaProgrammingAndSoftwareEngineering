import java.util.ArrayList;
import java.util.Collections;

public class FourthRatings {

    private double getAverageByID(String movieId, int minimalRaterNum) {
        int numOfRatings = 0;
        double totalRatings = 0;
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
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
        ArrayList<String> movieIds = MovieDatabase.filterBy(new TrueFilter());
        for (String id : movieIds) {
            double avgRating = getAverageByID(id, minimalRaterNum);
            if (avgRating == 0.0) {
                continue;
            }
            Rating rating = new Rating(id, avgRating);
            ratingList.add(rating);
        }
        return ratingList;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> ratingList = new ArrayList<Rating>();
        ArrayList<String> movieId = MovieDatabase.filterBy(filterCriteria);
        for (String id : movieId) {
            double avgRating = getAverageByID(id, minimalRaters);
            if (avgRating == 0.0) {
                continue;
            }
            Rating rating = new Rating(id, avgRating);
            ratingList.add(rating);
        }
        return ratingList;
    }

    private double dotProduct(Rater me, Rater other) {
        double similarity = 0.0;
        ArrayList<String> myMovies = me.getItemsRated();
        ArrayList<String> otherMovies = other.getItemsRated();
        for (String id1 : myMovies) {
            for (String id2 : otherMovies) {
                if (id1.equals(id2)) {
                    similarity += (me.getRating(id1) - 5) * (other.getRating(id2) - 5);
                }
            }
        }
        return similarity;
    }

    private ArrayList<Rating> getSimilarities(String me) {
        ArrayList<Rating> weights = new ArrayList<Rating>();
        Rater user = RaterDatabase.getRater(me);
        for (Rater r : RaterDatabase.getRaters()) {
            String userId = r.getID();
            if (!userId.equals(me)) {
                weights.add(new Rating(userId, dotProduct(user, r)));
            }
        }
        Collections.sort(weights, Collections.reverseOrder());
        return weights;
    }

    public ArrayList<Rating> getSimilarRating(String raterID, int numSimilarRaters, int minimalRaters) {
        return getSimilarRatingByFilter(raterID, numSimilarRaters, minimalRaters, new TrueFilter());
    }

    public ArrayList<Rating> getSimilarRatingByFilter(String raterID, int numSimilarRaters, int minimalRaters, Filter filter) {
        ArrayList<Rating> weights = getSimilarities(raterID);
        ArrayList<Rating> movieInfo = new ArrayList<Rating>();
        ArrayList<String> movieIds = MovieDatabase.filterBy(filter);
        for (String movieId : movieIds) {
            int numOfRatings = 0;
            double totalRatings = 0.0;
            for (int i = 0; i < numSimilarRaters; i++) {
                Rating userRating = weights.get(i);
                Double weight = userRating.getValue();
                String userId = userRating.getItem();
                double rating = RaterDatabase.getRater(userId).getRating(movieId);
                if (rating != -1) {
                    totalRatings += rating * weight;
                    numOfRatings++;
                }
            }
            if (!(numOfRatings < minimalRaters)) {
                movieInfo.add(new Rating(movieId, (totalRatings) / numOfRatings));
            }
        }

        Collections.sort(movieInfo, Collections.reverseOrder());
        return movieInfo;
    }
}






