import edu.duke.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts){
        int maxLen = counts.length-1;
//        System.out.println(maxLen);
        for (String word: resource.words()){
//            System.out.println(word);
            int length = word.length();
            if (!Character.isLetter(word.charAt(length-1))){
                length --;
            }
            if (!Character.isLetter(word.charAt(0))){
                length --;
            }
//            System.out.println(length);
            if (length >= maxLen){
                counts[maxLen]++;
            }
            else if (length >  0) {
                counts[length]++;
            }
        }
    }

    public void testCountWordLengths(){
        FileResource resource = new FileResource();
        int[] cnt = new int[31];
        countWordLengths(resource,cnt);
        for (int i = 0; i < cnt.length; i++){
//            System.out.println(i +" "+ cnt[i]);
            if (cnt[i] > 0){
                System.out.println(cnt[i]+ " words of length "+i);
            }
        }
        System.out.println(indexOfMax(cnt));
    }

    public int indexOfMax(int[] values){
        int maxInd = 0;
        for (int i = 0; i < values.length; i++){
            if (values[i] > values[maxInd]){
                maxInd = i;
            }
        }
        return maxInd;
    }

}
