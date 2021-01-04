import java.util.HashMap;
import java.util.ArrayList;
import static java.util.stream.Collectors.toCollection;

public class EfficientRater implements Rater {

    private String myID;
    private HashMap<String, Rating> movieRatingMap;

    public EfficientRater(String id) {
        myID = id;
        movieRatingMap = new HashMap<String, Rating>();
    }


    @Override
    public void addRating(String item, double rating) {
        movieRatingMap.put(item, new Rating(item, rating));
    }

    @Override
    public boolean hasRating(String item) {
        return movieRatingMap.containsKey(item);
    }

    @Override
    public String getID() {
        return myID;
    }

    @Override
    public double getRating(String item) {
        if (!movieRatingMap.containsKey(item)) {
            return -1;
        }
        return movieRatingMap.get(item).getValue();
    }

    @Override
    public int numRatings() {
        return movieRatingMap.size();
    }

    @Override
    public ArrayList<String> getItemsRated() {
        return movieRatingMap.keySet().stream().collect(toCollection(ArrayList::new));
    }

    @Override
    public String toString() {
        return "EfficientRater{" +
                "myID='" + myID + '\'' +
                "number of ratings="+movieRatingMap.size()+'}';
    }
}
