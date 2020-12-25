public class FirstRatingsMain {
    public static void main(String[] args){
//        FirstRatings firstRatings = new FirstRatings();
//        firstRatings.testLoadMovies("data/ratedmoviesfull.csv");
//        firstRatings.testLoadRaters("data/ratings.csv","193","1798709");
        MovieRunnerAverage movieRunnerAvg = new MovieRunnerAverage();
        movieRunnerAvg.printAverageRatings();
        movieRunnerAvg.getAverageRatingOneMovie();


    }
}
