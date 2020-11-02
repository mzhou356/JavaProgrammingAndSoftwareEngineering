import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import edu.duke.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class EarthQuakeTest {
    public static void main(String[] args) {
        EarthQuakeClient2 test1 = new EarthQuakeClient2();
//        test1.quakesWithFilter();
        test1.testMatchAllFilter2();
//        EarthQuakeClient test2 = new EarthQuakeClient();
//        test2.quakesOfDepth();
//        test2.quakesByPhrase();
//        test1.quakesWithFilter();
//        test1.closeToMe();
//        test1.quakesOfDepth();
//        test1.quakesByPhrase();
//        ClosestQuakes test2 = new ClosestQuakes();
//        test2.findClosestQuakes();
//        LargestQuakes test3 = new LargestQuakes();
//        test3.findLargestQuakes();

    }
}