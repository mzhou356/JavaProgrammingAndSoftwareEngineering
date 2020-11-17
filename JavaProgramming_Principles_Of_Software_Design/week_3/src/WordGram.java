import javafx.beans.binding.StringBinding;

import java.util.*;

public class WordGram{
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size){
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index){
        if (index < 0 || index >= myWords.length){
            throw new IndexOutOfBoundsException("bad index "+index);
        }
        return myWords[index];
    }

    public String[] getMyWords(){
        return myWords;
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        StringBuilder st = new StringBuilder();
        for (String word:myWords){
            st.append(word+" ");
        }
        return st.toString().trim();
    }

    public boolean equals(Object o){
        WordGram other = (WordGram) o;
        if (length() != other.length()){
            return false;
        }
        for (int i=0; i<length();i++){
            if (!wordAt(i).equals(other.wordAt(i))){
                return false;
            }
        }
        return true;
    }

    public WordGram shiftAdd(String word){
        WordGram out = new WordGram(myWords,0,myWords.length);
        String[] shifted = out.getMyWords();
        for (int i=0; i<out.length()-1;i++){
            shifted[i] = wordAt(i+1);
        }
        shifted[length()-1]=word;
//        String[] newWords = new String[length()];
//        for (int i=1; i<length();i++){
//            newWords[i-1] = wordAt(i);
//        }
//        newWords[length()-1] = word;
//        WordGram ans = new WordGram(newWords,0,length());
        return out;
    }

    public int hashCode(){
        String strKey = toString();
        myHash = strKey.hashCode();
        return myHash;
    }

}
