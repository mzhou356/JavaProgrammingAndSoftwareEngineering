import java.util.*;

public class MarkovModel extends AbstractMarkovModel{
    private int myOrder;

    public MarkovModel(int n) {
        myOrder = n;
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-myOrder);
        String key = myText.substring(index,index+myOrder);
        sb.append(key);
        for(int k=0; k < numChars-myOrder; k++){
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

    public String toString(){
        return "MarkovModel of order "+myOrder;
    }
}
