import java.util.*;

public class MarkovFour {
    private String myText;
    private Random myRandom;

    public MarkovFour() {
        myRandom = new Random();
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
            if (ind == -1){
                break;
            }
            if ((ind+key.length()) > myText.length()-1){
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
        int index = myRandom.nextInt(myText.length()-4);
        String key = myText.substring(index,index+4);
        sb.append(key);
        for(int k=0; k < numChars-4; k++){
            ArrayList<String> follows = getFollows(key);
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1)+next;
        }
        return sb.toString();
    }
}
