import java.util.*;

public class MarkovWordOne implements IMarkovModel{
    public String[] myText;
    private Random myRandom;

    public MarkovWordOne(){
        myRandom = new Random();
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public int indexOf(String[] words, String target, int start){
        for (int k = start; k<words.length;k++){
            if (words[k].equals(target)){
                return k;
            }
        }
        return -1;
    }


    public ArrayList<String> getFollows(String key){
        ArrayList<String> ans = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length){
            int start = indexOf(myText, key, pos);
            if (start == -1){
                break;
            }
            if (start +1 > myText.length-1){
                break;
            }
            String word = myText[start+1];
            ans.add(word);
            pos = start+1;
        }
        return ans;
    }

    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for (int i=0; i<numWords-1;i++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size()==0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }
        return sb.toString().trim();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public String toString(){
        return "MarkovModel of order wordOne";
    }
}
