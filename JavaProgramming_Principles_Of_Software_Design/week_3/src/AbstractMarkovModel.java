import java.util.*;
public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;  // accessible from subclass instead of private not accessible
    protected Random myRandom;

    public AbstractMarkovModel(){
        myRandom = new Random();
    }

    public void setTraining(String text){
        myText = text;
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }


    // this is why this class is abstract
    abstract public String getRandomText(int numChar);

    // accessible by subclasses
    protected ArrayList<String> getFollows(String key){
        ArrayList<String> ans = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length()){
            int ind = myText.indexOf(key,pos);
            if (ind == -1 || (ind + key.length() > myText.length() - 1)) {
                break;
            }
            String word = myText.substring(ind+key.length(), ind+key.length()+1);
            ans.add(word);
            pos = ind+1;
        }
        return ans;
    }
}
