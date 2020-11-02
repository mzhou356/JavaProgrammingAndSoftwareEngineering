public class MagnitudeFilter implements Filter {
    private double minMag;
    private double maxMag;

    public MagnitudeFilter(double minM,double maxM){
        minMag = minM;
        maxMag = maxM;
    }

    public boolean satisfies(QuakeEntry qe){
        double mag = qe.getMagnitude();
        if (mag >= minMag && mag <= maxMag){
            return true;
        }
        return false;
    }

    public String getName(){
        return "magnitude filter";
    }

}
