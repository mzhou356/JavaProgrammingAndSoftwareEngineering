import java.util.Random;
public class MarkovZero {
    public String myText;
    public Random myRandom;

    public MarkovZero(){
        myRandom = new Random();
    }

    public void setTraining(String s){
        myText = s;
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public String getRandomText(int numChars, int n){
        if (myText == null) return "";
        StringBuilder sb = new StringBuilder();
        for (int k=0; k < numChars; k++){
            int index = myRandom.nextInt(myText.length());
            sb.append(myText.charAt(index));
        }
        return sb.toString();
    }


}
