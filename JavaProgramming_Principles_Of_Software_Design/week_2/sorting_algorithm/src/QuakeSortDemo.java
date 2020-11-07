
/**
 * Illustrate sorting
 * @author Duke Software 
 * @version 1.0
 */

import java.util.*;

public class QuakeSortDemo {

    public void testSort(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";

        ArrayList<QuakeEntry> list  = parser.read(source);
//        Collections.sort(list, new MagnitudeComparator());
    Location loc1 = new Location(35.9886, -78.9072);
        Collections.sort(list, new DistanceComparator(loc1));

        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
    }
}