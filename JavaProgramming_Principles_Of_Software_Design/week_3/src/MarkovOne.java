import java.util.*;

public class MarkovOne extends AbstractMarkovModel {

    public MarkovOne() {
    }

    public void setTraining(String s){
        myText = s.trim();
    }

//    public ArrayList<String> getFollows(String key){
//        ArrayList<String> ans = new ArrayList<String>();
//        int pos = 0;
//        while (pos < myText.length()){
//            int ind = myText.indexOf(key,pos);
//            if (ind == -1){
//                break;
//            }
//            if ((ind+key.length()) > myText.length()-1){
//                break;
//            }
//            String word = myText.substring(ind+key.length(), ind+key.length()+1);
//            ans.add(word);
//            pos = ind+key.length();
//        }
//        return ans;
//    }


    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index,index+1);
        sb.append(myText.charAt(index));
        for(int k=0; k < numChars; k++){
            ArrayList<String> follows = getFollows(key);
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1)+next;
        }
        return sb.toString();
    }

    public String toString(){
        return "MarkovModel of order one";
    }
}

