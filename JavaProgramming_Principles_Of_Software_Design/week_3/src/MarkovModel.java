
import java.util.*;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int N;

    public MarkovModel(int n) {
        myRandom = new Random();
        N = n;
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public ArrayList<String> getFollows(String key){
        ArrayList<String> ans = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length()){
            int ind = myText.indexOf(key,pos);
//            if (ind == -1){
//                break;
//            }
//            if ((ind+key.length()) > myText.length()-1){
//                break;
//            }
            if (ind == -1 || (ind + key.length() > myText.length() - 1)) {
                break;
            }
            String word = myText.substring(ind+key.length(), ind+key.length()+1);
            ans.add(word);
            pos = ind+key.length();
        }
        return ans;
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-N);
        String key = myText.substring(index,index+N);
        sb.append(key);
        for(int k=0; k < numChars-N; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1)+next;
        }
        return sb.toString();
    }
}
