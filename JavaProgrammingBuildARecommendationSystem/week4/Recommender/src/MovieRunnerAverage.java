import java.util.*;
public class MovieRunnerAverage {

    public Rating getLowestRating(ArrayList<Rating> ratingList){
        Rating lowestRating = null;
        Double lowest = 10.0;
        for (int i=0; i<ratingList.size(); i++){
            Rating rating = ratingList.get(i);
            double score = rating.getValue();
            if (score < lowest){
                lowestRating = rating;
                lowest = score;
            }
        }
        return lowestRating;
    }

    public void printAverageRatings(){
        SecondRatings secondRatings = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
//        System.out.println(secondRatings.getMovieSize());
//        System.out.println(secondRatings.getRaterSize());
        ArrayList<Rating> ratingList = secondRatings.getAverageRatings(50);
        int num = 0;
        int length = ratingList.size();

        Rating lowest = null;
        for (int k=0; k < length; k++){
            lowest = getLowestRating(ratingList);
            ratingList.remove(lowest);
            if (lowest.getValue() == 0.0){
                continue;
            }
            num++;
            System.out.println(lowest.getValue()+" "+secondRatings.getTitle(lowest.getItem()));

        }
        System.out.println("Movies with 50 or more raters: "+ num);
    }

    public void getAverageRatingOneMovie(){
        SecondRatings secondRatings = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        ArrayList<Rating> ratingList = secondRatings.getAverageRatings(1);
        String movieID = secondRatings.getID("Vacation");
        for (Rating rating: ratingList){
            if (rating.getItem().equals(movieID)){
                System.out.println("The average rating for movie "+" Vacation "+rating.getValue());
                break;
            }
        }
    }
}
