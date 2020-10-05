
/**
 * Write a description of part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class part4 {
    public void linkFinder(String url){
        URLResource links = new URLResource(url);
        for (String word: links.words()){
            if (word.toLowerCase().indexOf("youtube.com")!=-1){
                int start = word.indexOf("\"");
                int end = word.lastIndexOf("\"");
            System.out.println(word.substring(start,end+1));
        }
        }
    }
    
    public void testLink(){
        linkFinder("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
    }

}
