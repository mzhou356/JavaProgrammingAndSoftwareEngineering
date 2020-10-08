
import java.util.ArrayList;
import edu.duke.*;

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> counts;

    public CharactersInPlay(){
        names = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }

    private void update(String person){
        if (! names.contains(person)){
            names.add(person);
            counts.add(1);
        } else{
            int ind = names.indexOf(person);
            int count = counts.get(ind)+1;
            counts.set(ind,count);
        }
    }

    private void findAllCharacters(){
        names.clear();
        counts.clear();
        FileResource fr = new FileResource();
        for (String line: fr.lines()){
            line = line.trim();
            int periodInd = line.indexOf(".");
            if (periodInd != -1 && periodInd != line.length()){
                String person = line.substring(0, periodInd);
                update(person);
            }
        }
    }

    private int findIndexOfMax(){
        int maxCount= 0;
        int maxInd = 0;
        for (int i=0; i < counts.size(); i++){
            int currCount = counts.get(i);
            if (currCount > maxCount){
                maxInd = i;
                maxCount = currCount;
            }
        }
        return maxInd;
    }

    private void charactersWithNumParts(int num1, int num2){
        for (int i=0; i < names.size(); i++){
            if (num1 <= counts.get(i) && counts.get(i) <= num2){
                System.out.println(names.get(i)+" "+counts.get(i));
            }
        }
    }

    public void tester(){
        findAllCharacters();
//        for (int i=0; i < names.size(); i++){
//            if (counts.get(i) > 50) {
//                System.out.println(names.get(i) + " " + counts.get(i));
//            }
//        }
        System.out.println("Character speakign parts between 10 and 15: ");
        charactersWithNumParts(50,100);
        int maxInd = findIndexOfMax();
        System.out.println("Character with the most speaking part is "+ names.get(maxInd)+" "+counts.get(maxInd));
    }
}
