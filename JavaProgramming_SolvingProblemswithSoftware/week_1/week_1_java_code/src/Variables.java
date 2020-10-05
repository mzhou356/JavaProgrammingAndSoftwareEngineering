/*
Introduce simple java concept, variables.
 */
public class Variables {
    public static void main(String[] args){
        int x = 4;
        int y = 5;
        int area = x*y;
        System.out.println("The width is " + x + " and the height is "+y+"; \n The area is "+ area);
        int x1;
        x1 = 4+3*2;
        int y1 = x-6;
        x1 = x1*y1;
        System.out.println("x1 is "+x1 + " and y1 is "+y1);
        System.out.println(areaFunc(x1,y1));

    }

    public static int areaFunc(int x, int y){
        return x*y;
    }
}
