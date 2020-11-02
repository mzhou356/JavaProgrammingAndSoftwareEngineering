public class DepthFilter implements Filter {
    private double minDepth;
    private double maxDepth;

    public DepthFilter(double minD, double maxD){
        minDepth = minD;
        maxDepth = maxD;
    }

    public boolean satisfies(QuakeEntry qe){
        double depth = qe.getDepth();
        if (depth >= minDepth && depth <= maxDepth){
            return true;
        }
        return false;
    }

    public String getName(){
        return "depth filter";
    }
}
