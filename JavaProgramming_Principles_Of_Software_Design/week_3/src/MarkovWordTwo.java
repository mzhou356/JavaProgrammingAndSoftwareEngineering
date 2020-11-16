import java.util.*;
public class MarkovWordTwo implements IMarkovModel{
    public String[] myText;
    private Random myRandom;

    public MarkovWordTwo(){
        myRandom = new Random();
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public int indexOf(String[] words, String target1, String target2, int start){
        for (int k = start; k<words.length;k++){
            if (words[k].equals(target1) && words[k+1].equals(target2)){
                return k+1;
            }
        }
        return -1;
    }


    public ArrayList<String> getFollows(String key1, String key2){
        ArrayList<String> ans = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length){
            int start = indexOf(myText, key1, key2, pos);
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
        int index = myRandom.nextInt(myText.length-2);
        String key1 = myText[index];
        String key2 = myText[index+1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        for (int i=0; i<numWords-2;i++){
            ArrayList<String> follows = getFollows(key1,key2);
            if (follows.size()==0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        return sb.toString().trim();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public String toString(){
        return "MarkovModel of order wordTwo";
    }
}

