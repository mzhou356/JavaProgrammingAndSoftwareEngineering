
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean Occurences(String stringa,String stringb){
        int first = stringb.indexOf(stringa);
        int second = stringb.lastIndexOf(stringa);
        if (first != second && first != -1 && second !=-1 ){
            return true;
        } 
        return false;
    }
    
    public void OccurencesTest(){
        System.out.println(Occurences("by","A story by Abby Long"));
        System.out.println(Occurences("a","banana"));
        System.out.println(Occurences("atg","ctgtatgta"));
        System.out.println(lastPart("by","A story by Abby Long"));
        System.out.println(lastPart("an","banana"));
        System.out.println(lastPart("zoo","forest"));
    }
    
    public String lastPart(String stringa,String stringb){
        int first = stringb.indexOf(stringa);
        if (first == -1){
            return stringa;
        } 
        return stringb.substring(first+stringa.length());
    }
}
