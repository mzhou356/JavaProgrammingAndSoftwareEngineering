import java.util.*;


public class EfficientMarkovModel extends AbstractMarkovModel{
    private int myOrder;
    public HashMap<String, ArrayList<String>> myMap;

    public EfficientMarkovModel(int n) {
        myOrder = n;
        myMap = new HashMap<String,ArrayList<String>>();
    }
    public void setTraining(String s){
        myText = s.trim();
        myMap.clear();
        buildMap();
        printHashMapInfo();
    }

    public void buildMap( ){
        for (int i=0; i<=(myText.length()-myOrder);i++){
            String key = myText.substring(i,i+myOrder);
//            System.out.println(key);
            if (!myMap.containsKey(key)){
                ArrayList<String> ans = new ArrayList<String>();
                int pos = 0;
                while (pos < myText.length()) {
                    int ind = myText.indexOf(key, pos);
                    if (ind == -1) {
                        break;
                    }
                    if (ind + key.length() > myText.length() - 1) {
                        break;
                    }
                    String word = myText.substring(ind + key.length(), ind + key.length() + 1);
                    ans.add(word);
                    pos = ind + 1;
                }
                myMap.put(key, ans);
                }
            }
    }

    protected ArrayList<String> getFollows(String key){
        return myMap.get(key);
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-myOrder);
        String key = myText.substring(index,index+myOrder);
//        System.out.println(key);
        sb.append(key);
        for(int k=0; k < numChars-myOrder; k++){
            ArrayList<String> follows = getFollows(key);
//            System.out.println(key+ ":" + follows);
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
        return "EfficientMarkovModel of  "+myOrder;
    }

    public void printHashMapInfo(){
        int maxSize = 0;
        String maxKey = "";
        for (String key: myMap.keySet()){
            ArrayList<String> value = myMap.get(key);
//            System.out.println(key+" : "+ value);
//            System.out.println("Array length is "+key+" :"+value.size());
            if (value.size() >= maxSize){
                maxSize = value.size();
                maxKey = key;
                System.out.println(key+": "+ key.length()+": "+ value.size());
            }
        }
        System.out.println("There are total "+ myMap.keySet().size()+" keys");
        System.out.println("The max key is "+ maxKey);
        System.out.println("The max count is "+maxSize);
    }
}

