import java.util.*;

public class LargestQuakes {
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for " + list.size());
        ArrayList<QuakeEntry> largest = getLargest(list,50);
        for(QuakeEntry qe: largest){
            System.out.println(qe);
        }
    }

    public int indexOfLargest(ArrayList<QuakeEntry> data){
        int ind = 0;
        double largestM = 0.0;
        for (int i=0; i < data.size(); i++){
            QuakeEntry qe = data.get(i);
            double mag = qe.getMagnitude();
            if (mag > largestM){
                largestM = mag;
                ind = i;
            }
        }
        return ind;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakedData, int howMany){
        if (howMany > quakedData.size()){
            howMany = quakedData.size();
        }
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakedData);
        ArrayList<QuakeEntry> rest = new ArrayList<QuakeEntry>();
        for (int i = 0; i < howMany; i++){
            int ind = indexOfLargest(copy);
            rest.add(copy.get(ind));
            copy.remove(ind);
        }
        return rest;
    }

    }

