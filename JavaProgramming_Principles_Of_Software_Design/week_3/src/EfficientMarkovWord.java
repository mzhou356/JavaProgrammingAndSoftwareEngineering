import java.util.*;

public class EfficientMarkovWord {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> myMap;

    public EfficientMarkovWord(int N){
        myRandom = new Random();
        myMap = new HashMap<WordGram, ArrayList<String>>();
        myOrder = N;
    }

    public void buildMap(){
        myMap.clear();
        for (int i=0; i<=myText.length-myOrder; i++){
            WordGram key = new WordGram(myText, i, myOrder);
            if (!myMap.containsKey(key)){
                ArrayList<String> value = new ArrayList<String>();
                myMap.put(key, value);
            }
            if ((i+myOrder) < myText.length) {
                String word = myText[i + myOrder];
                myMap.get(key).add(word);
            }
        }
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String txt){
        myText = txt.split("\\s+");
        buildMap();
    }

    public int indexOf(String[] words, WordGram target, int start){
        for (int k=start; k<=words.length-myOrder; k++){
            WordGram curr = new WordGram(words, k, myOrder);
            if (curr.equals(target)){
                return k;
            }
        }
        return -1;
    }

    public ArrayList<String> getFollows(WordGram kGram){
        return myMap.get(kGram);
//        ArrayList<String> follows = new ArrayList<String>();
//        int pos = 0;
//        while (pos < myText.length){
//            int ind = indexOf(myText, kGram, pos);
//            if (ind==-1 || (ind+myOrder)>(myText.length-1)){
//                break;
//            }
//            String word = myText[ind+myOrder];
//            follows.add(word);
//            pos = ind+1;
//        }
//        return follows;
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key.toString());
        sb.append(" ");
        for (int i=0; i<numWords-myOrder;i++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size()==0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        return sb.toString().trim();
    }

    public void printHashMapInfo(){
        int largest = 0;
        ArrayList<WordGram> largestKeys = new ArrayList<WordGram>();
        for (WordGram key: myMap.keySet()){
//            System.out.println(key+"\t"+myMap.get(key));
            int size = myMap.get(key).size();
            if (size > largest){
                largest = size;
            }
        }
        for (WordGram key: myMap.keySet()){
            if (myMap.get(key).size() == largest){
                largestKeys.add(key);
            }
        }
        System.out.println(myMap.size());
        System.out.println("Size of the largest value in hashmap is "+ largest);
        System.out.println("Keys with largest value in hashamp is "+ largestKeys);
    }
}
