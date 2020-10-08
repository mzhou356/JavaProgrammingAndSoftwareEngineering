import java.util.*;
import edu.duke.*;
import java.io.File;

public class UniqueWords {
//    private ArrayList<String> myWords;
//    private ArrayList<Integer> myFreqs;
    private HashMap<String, Integer> wordCounter;

    public UniqueWords(){
//        myWords = new ArrayList<String>();
//        myFreqs = new ArrayList<Integer>();
        wordCounter = new HashMap<String,Integer>();
    }

    private void findUnique(){
//        myWords.clear();
//
        wordCounter.clear();
        FileResource fr = new FileResource();
        for (String word : fr.words()) {
            word = word.toLowerCase();
            if(!wordCounter.containsKey(word)){
                wordCounter.put(word,1);
            } else{
                wordCounter.put(word,wordCounter.get(word)+1);
            }
//            if (!myWords.contains(word)) {
//                myWords.add(word);
//                myFreqs.add(1);
//            }
//            else{
//                int ind = myWords.indexOf(word);
//                int count = myFreqs.get(ind)+1;
//                myFreqs.set(ind,count);
//            }
        }
    }

    private String getRandomWord() {
        Random rand = new Random();
        String[] keySet = wordCounter.keySet().toArray(new String[wordCounter.size()]);
        int index = rand.nextInt(wordCounter.size());
//        int index = rand.nextInt(myWords.size());
//        return myWords.get(index);
        return keySet[index];
    }

    private String findMax(){
        int maxCount= 0;
        String maxWord = "";
        for (String w: wordCounter.keySet()){
            if (wordCounter.get(w)>maxCount){
                maxCount = wordCounter.get(w);
                maxWord = w;
            }
        }
        return maxWord;
    }

    public void tester(){
        this.findUnique();
        System.out.println("There are total "+wordCounter.size()+" unique words");
        for (String w: wordCounter.keySet()){
            if (wordCounter.get(w)>500) {
                System.out.println(w + " " + wordCounter.get(w));
            }
        }
        String word = findMax();
        System.out.println("The word that occurs most often and its counts are "+word+" "+wordCounter.get(word));
    }
}
