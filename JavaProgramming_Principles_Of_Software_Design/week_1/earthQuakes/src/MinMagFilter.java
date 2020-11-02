public class MinMagFilter implements Filter {
    private double magMin;
    public MinMagFilter(double min){
        magMin = min;
    }

    // must define satisfies from Filter interface
    public boolean satisfies(QuakeEntry qe){
        return qe.getMagnitude() >=magMin;
    }

    public String getName(){
        return "min magnitude filter";
    }
}
