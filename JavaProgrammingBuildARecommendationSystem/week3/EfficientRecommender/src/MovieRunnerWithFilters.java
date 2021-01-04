import java.util.ArrayList;

public class MovieRunnerWithFilters {

    public Rating getLowestRating(ArrayList<Rating> ratingList){
        Rating lowestRating = null;
        Double lowest = 10.0;
        for (int i=0; i<ratingList.size(); i++){
            Rating rating = ratingList.get(i);
            double score = rating.getValue();
            if (score <= lowest){
                lowestRating = rating;
                lowest = score;
            }
        }
        return lowestRating;
    }

    public void printAverageRatings(){
        ThirdRatings thirdRatings = new ThirdRatings("data/ratings.csv");
        System.out.println("There are total " + thirdRatings.getRaterSize()+" raters");
        ArrayList<Rating> ratingList = thirdRatings.getAverageRatings(35);
        System.out.println("There are total "+ MovieDatabase.size()+" movies");
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
//            System.out.println(lowest.getValue()+" "+ MovieDatabase.getTitle(lowest.getItem()));

        }
        System.out.println("Movies with 35 or more raters: "+ num);
    }

    public void printAverageRatingsByYear(){
        ThirdRatings thirdRatings = new ThirdRatings("data/ratings.csv");
        System.out.println("There are total " + thirdRatings.getRaterSize()+" raters");
        ArrayList<Rating> ratingList = thirdRatings.getAverageRatingsByFilter(20, new YearAfterFilter(2000));
//        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are total "+ MovieDatabase.size()+" movies");
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
//            System.out.println(lowest.getValue()+" "+ MovieDatabase.getTitle(lowest.getItem()));

        }
        System.out.println("Matched movies: "+ num);
    }

    public void printAverageRatingsByGenre(){
        ThirdRatings thirdRatings = new ThirdRatings("data/ratings.csv");
        System.out.println("There are total " + thirdRatings.getRaterSize()+" raters");
        ArrayList<Rating> ratingList = thirdRatings.getAverageRatingsByFilter(20, new GenreFilter("Comedy"));
//        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are total "+ MovieDatabase.size()+" movies");
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
//            System.out.println(lowest.getValue()+" "+ MovieDatabase.getTitle(lowest.getItem()));
            System.out.println(MovieDatabase.getGenres(lowest.getItem()));

        }
        System.out.println("Matched movies: "+ num);
    }

    public void printAverageRatingsByMinutes(){
        ThirdRatings thirdRatings = new ThirdRatings("data/ratings.csv");
        System.out.println("There are total " + thirdRatings.getRaterSize()+" raters");
        ArrayList<Rating> ratingList = thirdRatings.getAverageRatingsByFilter(5, new MinutesFilter(105, 135));
//        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are total "+ MovieDatabase.size()+" movies");
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
            System.out.println(lowest.getValue()+" "+ " Time: "+MovieDatabase.getMinutes(lowest.getItem())+" "+MovieDatabase.getTitle(lowest.getItem()));
        }
        System.out.println("Matched movies: "+ num);
    }

    public void printAverageRatingsByDirectors(){
        ThirdRatings thirdRatings = new ThirdRatings("data/ratings.csv");
        System.out.println("There are total " + thirdRatings.getRaterSize()+" raters");
        ArrayList<Rating> ratingList = thirdRatings.getAverageRatingsByFilter(4, new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));
//        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are total "+ MovieDatabase.size()+" movies");
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
//            System.out.println(lowest.getValue()+" "+ MovieDatabase.getTitle(lowest.getItem()));
            System.out.println(MovieDatabase.getDirector(lowest.getItem()));
        }
        System.out.println("Matched movies : "+ num);
    }

    public void printAverageRatingsByYearAfterAndGenre(){
        ThirdRatings thirdRatings = new ThirdRatings("data/ratings.csv");
        System.out.println("There are total " + thirdRatings.getRaterSize()+" raters");
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new GenreFilter("Drama"));
        allFilters.addFilter(new YearAfterFilter(1990));
        ArrayList<Rating> ratingList = thirdRatings.getAverageRatingsByFilter(8, allFilters);
//        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are total "+ MovieDatabase.size()+" movies");
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
//            System.out.println(lowest.getValue()+" "+ MovieDatabase.getYear(lowest.getItem())+" "+MovieDatabase.getTitle(lowest.getItem()));
            System.out.println(MovieDatabase.getGenres(lowest.getItem()));

        }
        System.out.println("Matched movies: "+ num);
    }

    public void printAverageRatingsByDirectorsAndMinutes(){
        ThirdRatings thirdRatings = new ThirdRatings("data/ratings.csv");
        System.out.println("There are total " + thirdRatings.getRaterSize()+" raters");
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        allFilters.addFilter(new MinutesFilter(90, 180));
        ArrayList<Rating> ratingList = thirdRatings.getAverageRatingsByFilter(3, allFilters);
//        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are total "+ MovieDatabase.size()+" movies");
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
//            System.out.println(lowest.getValue()+" Time: "+ MovieDatabase.getMinutes(lowest.getItem())+" "+MovieDatabase.getTitle(lowest.getItem()));
            System.out.println(MovieDatabase.getDirector(lowest.getItem()));

        }
        System.out.println("Matched movies: "+ num);
    }
}
