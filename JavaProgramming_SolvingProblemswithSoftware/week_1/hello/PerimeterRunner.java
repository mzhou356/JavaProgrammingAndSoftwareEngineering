
/**
 * Write a description of PerimeterRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class PerimeterRunner {
    public double getPerimeter(Shape s){
        double totalPerim = 0.0;
        Point prevPt = s.getLastPoint();
        
        for (Point currPt: s.getPoints()){
            double currDist = prevPt.distance(currPt);
            totalPerim += currDist;
            prevPt = currPt;
        }
        return totalPerim;
    }

}
