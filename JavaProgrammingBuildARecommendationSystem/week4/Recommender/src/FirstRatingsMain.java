import java.util.ArrayList;

public class FirstRatingsMain {
    public static void main(String[] args){
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        RecommendationRunner Rec = new RecommendationRunner();
//        System.out.format("There are total %s movies",
//                Rec.getItemsToRate().size());

//        for (String movie:Rec.getItemsToRate()){
//            System.out.println("Movie title: "+ MovieDatabase.getTitle(movie));
//            System.out.println("Movie genre: "+ MovieDatabase.getGenres(movie));
//        }
        Rec.printRecommendationsFor("956");
    }
}
