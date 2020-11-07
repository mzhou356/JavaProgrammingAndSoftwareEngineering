import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2){
        String[] title1 = q1.getInfo().split("\\s");
        String word1 = title1[title1.length-1];
        String[] title2 = q2.getInfo().split("\\s");
        String word2 = title2[title2.length-1];
        if (word1.compareTo(word2) == 0){
            return Double.compare(q1.getMagnitude(),q2.getMagnitude());
        }
        return word1.compareTo(word2);
    }
}