public class DistanceFilter implements Filter {
    private Location loc;
    private double maxDist;

    public DistanceFilter(Location where, double dist){
        loc = where;
        maxDist = dist;
    }

    public boolean satisfies(QuakeEntry qe){
        Location curr = qe.getLocation();
        double dist = curr.distanceTo(loc);
        if (dist < maxDist){
            return true;
        }
        return false;
    }

    public String getName(){
        return "distance filter";
    }
}
