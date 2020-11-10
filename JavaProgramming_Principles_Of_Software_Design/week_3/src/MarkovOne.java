import java.util.*;

public class MarkovOne extends MarkovZero {
    public ArrayList<String> getFollows(String key){
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos<(myText.length()-key.length())){
            int ind = myText.indexOf(key,pos);
            if (ind == -1 || (ind+key.length()) >= myText.length()){
                break;
            }
            follows.add(myText.substring(ind+key.length(),ind+key.length()+1));
            pos = ind+key.length();
            }
        return follows;
        }

    @Override
    public String getRandomText(int numChars, int n){
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length()-n);
//        System.out.println(index);
        String key = myText.substring(index, index+n);
//        System.out.println(key);
        sb.append(key);
        for (int k=0; k < numChars-n; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
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
