import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

public class RecommendationRunner implements Recommender {
    /*
    Helper method for getItemsRate, it turns an ArrayList with numOfRatings first and totalRatings second in the list.
     */

    private ArrayList<Double> getMovieInfoD(String movieId) {
        double numOfRatings = 0.0;
        double totalRatings = 0.0;
        ArrayList<Rater> allRaters = RaterDatabase.getRaters();
        ArrayList<Double> movieInfo = new ArrayList<Double>();
        for (Rater rater : allRaters) {
            double rating = rater.getRating(movieId);
            if (rating != -1) {
                totalRatings += rating;
                numOfRatings++;
            }
        }
        movieInfo.add(numOfRatings);
        movieInfo.add(totalRatings);
        return movieInfo;
    }

    /*
    Items selected will only look at movies made after 1980 and will look at top 100 highest rated movies
    and top 100 most rated movies. Randomly select 20 movies out the 200 choices. The min num of Ratings wil be 20
     */
    @Override
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> allMovies = MovieDatabase.filterBy(new YearAfterFilter(1980));
        ArrayList<Rating> allRatings = new ArrayList<Rating>();
        ArrayList<Rating> allCounts = new ArrayList<Rating>();
        for (String movieId : allMovies) {
            ArrayList<Double> movieInfo = getMovieInfoD(movieId);
            double RatingCount = movieInfo.get(0);
            if (RatingCount >= 5) {
                double avgRating = movieInfo.get(1) / RatingCount;
                allRatings.add(new Rating(movieId, avgRating));
                allCounts.add(new Rating(movieId, RatingCount));
            }
        }
        Collections.sort(allRatings, Collections.reverseOrder());
        Collections.sort(allCounts, Collections.reverseOrder());
        ArrayList<String> filteredMovies = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            filteredMovies.add(allRatings.get(i).getItem());
            filteredMovies.add(allCounts.get(i).getItem());
        }
        Random rand = new Random();
        HashSet<String> movieChosen = new HashSet<String>();
        int cnt = filteredMovies.size();
        while (movieChosen.size() < 25) {
            int randomInd = rand.nextInt(cnt);
            movieChosen.add(filteredMovies.get(randomInd));
        }

        ArrayList<String> movieChosenList = new ArrayList<String>(movieChosen);
        return movieChosenList;
    }


    @Override
    public void printRecommendationsFor(String webRaterID) {
        System.out.println("<html><body>");
        System.out.println("<style>");
        System.out.println("body {background-color:  SnowWhite}");
        System.out.println("h2 {font-size: 28pt; color: RoyalBlue; text-align: center}");
        System.out.println("table {border: 2px solid black; padding:5; background-color:white; margin-left:auto; margin-right:auto;}");
        System.out.println("th{font-size:18pt; color: Indigo; border: 1px black; text-align:center;border:1.5px solid gray;}");
        System.out.println("td{border:1px dotted darkgray; text-align:center; color: Black; font-size:12pt; bottom-margin:auto; top-margin:auto;}");
        System.out.println("</style>");
        FourthRatings ratingRunner = new FourthRatings();
        ArrayList<Rating> rec = ratingRunner.getSimilarRatingByFilter(webRaterID, 100, 5, new RepeatFilter(RaterDatabase.getRater(webRaterID)));
        int recSize = rec.size() >= 10 ? 10 : rec.size();
        if (recSize > 0) {
            System.out.println("<h2><b>Movie Recommendations For You</b></h2>");
            System.out.println("<table><tr><th>Movie Title</th><th>Year Made</th><th>Movie Length</th><th>Movie Genres</th><th>Similarity Rating</th></tr>");
            for (int i = 0; i < recSize; i++) {
                String movieId = rec.get(i).getItem();
                int rating = (int) Math.round(rec.get(i).getValue());
                String title = MovieDatabase.getTitle(movieId);
                int year = MovieDatabase.getYear(movieId);
                int length = MovieDatabase.getMinutes(movieId);
                String genres = MovieDatabase.getGenres(movieId);
                System.out.println("<tr><td>" + title + "</td><td>" + year + "</td><td>" + length + "</td><td>" + genres + "</td><td>" + rating + "</td></tr>");
            }
            System.out.println("</table></body></html>");
        } else {
            System.out.println("<h2><b>Sorry, we don't have enough data to provide you with good movie" +
                    " recommendations right now</b></h2></body></html>");
        }
    }
}
