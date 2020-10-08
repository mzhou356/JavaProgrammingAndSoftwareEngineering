import java.util.*;
import java.io.File;
import edu.duke.*;

public class WordsInFiles {
    private HashMap<String,ArrayList<String>> wordFileMap;

    public WordsInFiles(){
        wordFileMap = new HashMap<String,ArrayList<String>>();
    }

    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for (String w: fr.words()){
            if (wordFileMap.containsKey(w)){
                String fn = f.getName();
                ArrayList<String> fList = wordFileMap.get(w);
                if (!fList.contains(fn)) {
                    wordFileMap.get(w).add(f.getName());
                }
            }
            else{
                ArrayList<String> newList = new ArrayList<String>();
                newList.add(f.getName());
                wordFileMap.put(w,newList);
            }
        }
    }

    private void buildWordFileMap(){
        wordFileMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }

    private int maxNumber() {
        assert (!wordFileMap.isEmpty()):"WordFileMap is empty!";
        int most = 0;
        for (String w : wordFileMap.keySet()) {
            int currCT = wordFileMap.get(w).size();
            if (currCT > most) {
                most = currCT;
            }
        }
        return most;
    }

    private ArrayList<String> wordsInNumFiles(int number){
        assert(!wordFileMap.isEmpty());
        ArrayList<String> wordList = new ArrayList<String>();
        for (String w: wordFileMap.keySet()){
            int numFile = wordFileMap.get(w).size();
            if (numFile == number){
                wordList.add(w);
            }
        }
        return wordList;
    }

    private void printFileIn(String word){
        assert(!wordFileMap.isEmpty());
        if (wordFileMap.containsKey(word)) {
            ArrayList<String> wordList = wordFileMap.get(word);
            System.out.println(wordList);
            for (String name:wordList){
                System.out.println(name);
            }
        }
    }

    public void tester(){
        buildWordFileMap();
        if (wordFileMap.size() < 20){
            for (String w: wordFileMap.keySet()){
                System.out.println(w+" "+wordFileMap.get(w));
            }
        }
//        System.out.println("Most common count is "+ maxNumber());
        System.out.println("The word list for number equals 7 is "+wordsInNumFiles(7).size());
        System.out.println("The word list for number equals 4 is "+wordsInNumFiles(4).size());
        System.out.println("The files with words sea is ");
        printFileIn("sea");
        System.out.println("The files with words tree is ");
        printFileIn("tree");
    }
}
