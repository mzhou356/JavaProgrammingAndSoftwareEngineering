/*
This is the client class for point.java;
 */

public class Shape {
    public static void main(String[] args){
        point p1 = new point(3,4);
        point p2 = new point(6,8);
        System.out.println(p1.distance(p2));
        System.out.println(p1 == p2); // point to same object
        System.out.println(3==4);
    }
}
