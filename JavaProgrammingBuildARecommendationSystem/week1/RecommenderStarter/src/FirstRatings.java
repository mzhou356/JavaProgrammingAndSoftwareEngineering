import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String fileName){
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        FileResource csvFile = new FileResource(fileName);
        CSVParser csvParser = csvFile.getCSVParser(true, ",");
        for (CSVRecord csvRecord: csvParser){
            String id = csvRecord.get("id");
            String title = csvRecord.get("title");
            String year = csvRecord.get("year");
            String genres = csvRecord.get("genre");
            String director = csvRecord.get("director");
            String country = csvRecord.get("country");
            int minutes = Integer.parseInt(csvRecord.get("minutes"));
            String poster = csvRecord.get("poster");
            Movie movie = new Movie(id,title,year,genres,director,country,poster,minutes);
            movieList.add(movie);
        }
        return movieList;
    }

    public ArrayList<Rater> loadRaters(String fileName){
        ArrayList<Rater> raterList = new ArrayList<Rater>();
        FileResource fr = new FileResource(fileName);
        CSVParser csvParser = fr.getCSVParser(true,",");
        for (CSVRecord record:csvParser){
            String raterId = record.get("rater_id");
            String movieId = record.get("movie_id");
            Double rating = Double.parseDouble(record.get("rating"));
            int exists = 0;
            for (Rater rater: raterList) {
                if (rater.getID().equals(raterId)){
                    rater.addRating(movieId, rating);
                    exists = 1;
                }
            }
            if (exists == 0){
                Rater rater = new Rater(raterId);
                rater.addRating(movieId, rating);
                raterList.add(rater);
            }
        }
        return raterList;
    }

    public void testLoadMovies(String fileName){
        ArrayList<Movie> movieList = loadMovies(fileName);
        System.out.println("There are "+movieList.size()+" number of movies");
        int comedyCnt = 0;
        int longMovie = 0;
        HashMap<String, Integer> directorMovieCNT = new HashMap<String, Integer>();
        for (Movie movie: movieList){
//            System.out.println(movie);
            String genre = movie.getGenres();
            if (genre.indexOf("Comedy") != -1){
                comedyCnt ++;
            }
            int minutes = movie.getMinutes();
            if (minutes > 150){
                longMovie ++;
            }
            String[] directorList = movie.getDirector().split(",");
            for (String director:directorList){
                String dir = director.trim();
                if (directorMovieCNT.containsKey(dir)){
                    directorMovieCNT.put(dir,directorMovieCNT.get(dir)+1);
                } else{
                    directorMovieCNT.put(dir,1);
                }
            }
        }
        int maxCount = 0;
        ArrayList<String> directors = new ArrayList<String>();
        for (int cnt:directorMovieCNT.values()){
            if (cnt > maxCount){
                maxCount = cnt;
            }
        }
        for (String director:directorMovieCNT.keySet()){
            if (directorMovieCNT.get(director) == maxCount){
                directors.add(director);
            }
        }
        System.out.println("There are total "+comedyCnt+" number of movies that are Comedy");
        System.out.println("There are total "+longMovie+" number of movies that are longer than 150 min");
        System.out.println("The maximum number of movies by any director is "+ maxCount);
        System.out.println("The list of directors are "+ directors);
    }

    public void testLoadRaters(String fileName, String raterId, String movieId){
        ArrayList<Rater> raterList = loadRaters(fileName);
        System.out.println("There are total: "+raterList.size()+" number of raters");
        HashMap<String, Integer> raterCounts = new HashMap<String, Integer>();
        HashMap<String, Integer> movieCounts = new HashMap<String, Integer>();
        for (Rater rater:raterList){
            String id = rater.getID();
            ArrayList<String> movieList = rater.getItemsRated();
            if (!raterCounts.containsKey(id)){
                raterCounts.put(id, movieList.size());
               }
            for (String movie:movieList) {
                if (movieCounts.containsKey(movie)){
                    movieCounts.put(movie, movieCounts.get(movie)+1);
            } else {
                    movieCounts.put(movie,1);
                }
           }
        }
        int maxCount = 0;
        for (int cnts:raterCounts.values()){
            if (cnts > maxCount){
                maxCount = cnts;
            }
        }
        ArrayList<String> raterIds = new ArrayList<String>();
        for (String id: raterCounts.keySet()){
            if (raterCounts.get(id) == maxCount){
                raterIds.add(id);
            }
        }
        System.out.println("The number of ratings for rater "+raterId + " is "+raterCounts.get(raterId));
        System.out.println("The maximum number of ratings by any  user is "+maxCount);
        System.out.println("The raters for the max count are "+raterIds);
        System.out.println("The number of ratings for movie "+movieId + " is "+ movieCounts.get(movieId));
        System.out.println("There are "+ movieCounts.size() +" different movies that have been rated by all these raters");

    }
}
