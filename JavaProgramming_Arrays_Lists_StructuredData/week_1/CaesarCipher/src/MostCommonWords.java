import edu.duke.*;

public class MostCommonWords {
    void countShakespear(){
        String[] plays = {"caesar.txt","errors.txt","hamlet.txt","likeit.txt","macbeth.txt","romeo.txt"};

        String[] common = getCommon();
        int[] counts = new int[common.length];
        for (int k=0; k < plays.length;k++){
            FileResource resource = new FileResource("CommonWordsData/"+plays[k]);
            countWords(resource,common,counts);
            System.out.println("done with "+plays[k]);

        }
        for (int k=0; k < common.length; k++){
            System.out.println(common[k]+"\t"+counts[k]);
        }
    }

    public String[] getCommon(){
        FileResource resource = new FileResource("CommonWordsData/common.txt");
        String[] common = new String[20];
        int index = 0;
        for (String s: resource.words()){
            common[index] = s;
            index ++;
        }
        return common;
    }

    public int indexOf(String[] list,String word){
        for (int i = 0; i < list.length; i++){
            if (list[i].equalsIgnoreCase(word)){
                return i;
            }
        }
        return -1;
    }

    public void countWords(FileResource resource,String[] commons,int[] counts){
        for (String word: resource.words()){
            word = word.toLowerCase();
            int index = indexOf(commons,word);
            if (index !=-1) {
                counts[index] ++;
            }
        }
    }
}
