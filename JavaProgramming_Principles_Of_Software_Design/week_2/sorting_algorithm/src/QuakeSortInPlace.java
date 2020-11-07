/**
 * Write a description of class QuakeSortInPlace here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import com.apple.laf.AquaKeyBindings;

import java.util.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        for (int i = 0; i<quakes.size()-1; i++){
            if (quakes.get(i).getMagnitude() > quakes.get(i+1).getMagnitude()){
                return false;
            }
        }
        return true;
    }

    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from) {
        int maxIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }


    public void sortByMagnitude(ArrayList<QuakeEntry> in) {

        for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }

    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {

        for (int i=0; i< in.size(); i++) {
            if (checkInSortedOrder(in)){
                System.out.println("This requirest "+ i + " passes");
                break;
            }
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }

    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {

        for (int i=0; i< 70; i++) {
            int maxIdx = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i,qmax);
            in.set(maxIdx,qi);
        }

    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        int numSorted = 1;
        while (numSorted < in.size()){
//            for (QuakeEntry qe: in) {
//                System.out.println(qe);
//            }
//            System.out.println();
            onePassBubbleSort(in, numSorted);
            numSorted++;
        }
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        int numSorted = 1;
        while (numSorted < in.size()){
//            for (QuakeEntry qe: in) {
//                System.out.println(qe);
//            }
//            System.out.println();
            if (checkInSortedOrder(in)){
                System.out.println("This requires:" + (numSorted-1) + " passes");
                break;
            }
            onePassBubbleSort(in, numSorted);
            numSorted++;
        }
    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        for (int i = 0; i < quakeData.size()-numSorted; i++){
            QuakeEntry first = quakeData.get(i);
            QuakeEntry second = quakeData.get(i+1);
            if (first.getMagnitude() > second.getMagnitude()){
                quakeData.set(i, second);
                quakeData.set(i+1, first);
            }
        }
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
//        String source = "src/data/earthQuakeDataDec6sample2.atom.txt";
        String source = "src/data/earthQuakeDataWeekDec6sample2.atom.txt";
//        String source = "src/data/nov20quakedatasmall.atom";
        //String source = "src/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);

        System.out.println("read data for "+list.size()+" quakes");
//        sortByMagnitude(list);
//        sortByMagnitudeWithCheck(list);
//        sortByLargestDepth(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        for (QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "src/data/nov20quakedata.atom";
        String source = "src/data/nov20quakedatasmall.atom";
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getInfo());
        }

    }
}

