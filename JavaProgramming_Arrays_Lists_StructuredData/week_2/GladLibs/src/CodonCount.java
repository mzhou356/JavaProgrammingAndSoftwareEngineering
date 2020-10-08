import java.util.*;
import edu.duke.*;
public class CodonCount {
    private HashMap<String,Integer> counts;

    public CodonCount(){
        counts = new HashMap<String,Integer>();
    }

    private void buildCodonMap(int start, String dna){
        counts.clear();
        dna = dna.trim();
        for (int i=start;i<dna.length();i+=3){
            if ((i+3) <= dna.length()) {
                String curr = dna.substring(i, i + 3);
                if (counts.containsKey(curr)){
                    counts.put(curr,counts.get(curr)+1);
                }
                else {
                    counts.put(curr,1);
                }
            }
        }
    }

    private String getMostCommonCodon(){
        if (counts.isEmpty()){
            return "build map first!";
        }
        int most = 0;
        String codon = "";
        for (String k: counts.keySet()){
            int currC = counts.get(k);
            if (currC > most){
                most = currC;
                codon = k;
            }
        }
        return codon;
    }

    private void printCodonCounts(int start, int end){
        for (String k: counts.keySet()){
            int cnt = counts.get(k);
            if (cnt >=start && cnt <= end){
                System.out.println(k+" "+cnt);
            }
        }
    }

    public void tester(){
        String dna = new FileResource().asString().toUpperCase();
        int[] frame = {0,1,2};
        for (int i:frame) {
            buildCodonMap(i, dna);
            System.out.println("Reading frame starting with " + i + " results in " + counts.size() + " unique codons.");
            String codon = getMostCommonCodon();
            System.out.println("and most common codon is " + codon + " with count " + counts.get(codon));
            System.out.println("Counts of codons between 1 and 5 inclusive are:");
            printCodonCounts(7, 7);
        }
    }



}
