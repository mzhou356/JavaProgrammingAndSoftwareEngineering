import java.util.*;

public class MarkovWord {
    private String[] myText;
    private Random myRandom;
    private int myOrder;

    public MarkovWord(int N){
        myRandom = new Random();
        myOrder = N;
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String txt){
        myText = txt.split("\\s+");
    }

    public int indexOf(String[] words, WordGram target, int start){
        for (int k=start; k<words.length-myOrder; k++){
            WordGram curr = new WordGram(words, k, myOrder);
            if (curr.equals(target)){
                return k;
            }
        }
        return -1;
    }

    public ArrayList<String> getFollows(WordGram kGram){
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length){
            int ind = indexOf(myText, kGram, pos);
            if (ind==-1 || (ind+myOrder)>(myText.length-1)){
                break;
            }
            String word = myText[ind+myOrder];
            follows.add(word);
            pos = ind+1;
        }
        return follows;
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
}
