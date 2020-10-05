import edu.duke.*;

public class PerimeterRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }
    public int getNumPoints(Shape s) {
        int totalNum = 0;
        for (Point p: s.getPoints()){
            totalNum += 1;
        }
        return totalNum;
    }
    
    public double getAverageLength(Shape s){
        return (double) (getPerimeter(s)/getNumPoints(s));
    }
    
    public double getLargestSide(Shape s){
        double longest = 0.0; 
        Point prevPt = s.getLastPoint();
        for (Point p: s.getPoints()){
            double currDist = prevPt.distance(p);
            if (currDist > longest){
                longest = currDist;
            }
            prevPt = p;
        }
        return longest;
    }
    
    public double getLargestX(Shape s){
        double largest = 0.0;
        for (Point p: s.getPoints()){
            double currX = p.getX();
            if (currX > largest){
                largest = currX;
            }
        }
         return largest;
        }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println("Total points = " + getNumPoints(s));
        System.out.println("Avg length = " + getAverageLength(s));
        System.out.println("Longest side = " + getLargestSide(s));
        System.out.println("Largest X = " + getLargestX(s));
    }
    
    
    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeter();
    }
}
