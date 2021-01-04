import edu.duke.DirectoryResource;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {

    public void printAverageRatings(){
        FourthRatings fourthRatings = new FourthRatings();
        ArrayList<Rating> ratingList = fourthRatings.getAverageRatings(1);
        System.out.println("There are total " + RaterDatabase.size()+" raters");
        System.out.println("There are total "+ MovieDatabase.size()+" movies");
        System.out.println("Matched movies: "+ ratingList.size());
        Collections.sort(ratingList);
        for (Rating rating:ratingList){
            System.out.println(rating.getValue()+" "+ MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByYearAfterAndGenre(){
        FourthRatings fourthRatings = new FourthRatings();
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new GenreFilter("Romance"));
        allFilters.addFilter(new YearAfterFilter(1980));
        ArrayList<Rating> ratingList = fourthRatings.getAverageRatingsByFilter(1, allFilters);
        System.out.println("There are total " + RaterDatabase.size() +" raters");
        System.out.println("There are total "+ MovieDatabase.size()+" movies");
        System.out.println("Matched movies: "+ ratingList.size());
        Collections.sort(ratingList);
        for (Rating rating:ratingList){
            System.out.println(rating.getValue()+" "+ MovieDatabase.getYear(rating.getItem())+" "+MovieDatabase.getTitle(rating.getItem()));
            System.out.println(MovieDatabase.getGenres(rating.getItem()));
            }
    }

    public void printSimilarRatings(){
        FourthRatings fourthRatings = new FourthRatings();
        ArrayList<Rating> rec= fourthRatings.getSimilarRating("71", 20,5);
        System.out.println("The movie with the top rated average is "+ MovieDatabase.getTitle(rec.get(0).getItem()));
    }

    public void printSimilarRatingsByGenre(){
        FourthRatings fourthRatings = new FourthRatings();
        GenreFilter filter = new GenreFilter("Mystery");
        ArrayList<Rating> rec= fourthRatings.getSimilarRatingByFilter("964", 20,5, filter);
        System.out.println("The movie with the top rated average is "+ MovieDatabase.getTitle(rec.get(0).getItem()));
    }

    public void printSimilarRatingsByDirector(){
        FourthRatings fourthRatings = new FourthRatings();
        DirectorsFilter filter = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> rec= fourthRatings.getSimilarRatingByFilter("120", 10,2, filter);
        System.out.println("The movie with the top rated average is "+ MovieDatabase.getTitle(rec.get(0).getItem()));
    }

    public void printSimilarRatingsByGenreAndMinutes(){
        FourthRatings fourthRatings = new FourthRatings();
        AllFilters filter = new AllFilters();
        filter.addFilter(new GenreFilter("Drama"));
        filter.addFilter(new MinutesFilter(80,160));
        ArrayList<Rating> rec= fourthRatings.getSimilarRatingByFilter("168", 10,3, filter);
        System.out.println("The movie with the top rated average is "+ MovieDatabase.getTitle(rec.get(0).getItem()));
    }

    public void printSimilarRatingsByYearAfterAndMinutes(){
        FourthRatings fourthRatings = new FourthRatings();
        AllFilters filter = new AllFilters();
        filter.addFilter(new YearAfterFilter(1975));
        filter.addFilter(new MinutesFilter(70,200));
        ArrayList<Rating> rec= fourthRatings.getSimilarRatingByFilter("314", 10,5, filter);
        System.out.println("The movie with the top rated average is "+ MovieDatabase.getTitle(rec.get(0).getItem()));
    }
}
