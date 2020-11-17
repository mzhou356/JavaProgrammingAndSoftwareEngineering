/**
 * Write a description of Tester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import edu.duke.*;
public class Tester {
    public void testGetFollows(){
        MarkovOne test = new MarkovOne();
        String txt = new FileResource().asString();
        txt.replace("\n"," ");
        test.setTraining(txt);
        ArrayList<String> arr = test.getFollows("he");
        System.out.println(arr.size());
    }

}