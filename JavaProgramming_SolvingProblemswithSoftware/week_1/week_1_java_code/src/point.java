/*
Simple point class

A class is a template to make objects
 */

import java.lang.Math.*;
public class point {
    // variables, private fields , only point class code can access the variable
    private int x;  // default is zero
    private int y;
    // constructor
    public point(int startX,int startY){
        this.x = startX;
        this.y = startY;
    }
    // behaviors, getter and setter methods
    public int getX(){return this.x;}
    public int getY(){return this.y;}
    // other methods
    public double distance(point otherPt){
        int dx = this.x-otherPt.getX();
        int dy = this.y-otherPt.getY();
        //Euclidean distance
        return Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
    }

}

