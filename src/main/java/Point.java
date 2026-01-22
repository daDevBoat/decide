import static java.lang.Math.*;

public class Point {
    double x;
    double y;

    Point(double  x, double  y) {
        this.x = x;
        this.y = y;
    } 

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    public static double triangleArea(Point p1, Point p2, Point p3){
        double c = p1.x * (p2.y - p3.y) + p2.x * (p3.y - p1.y) + p3.x * (p1.y - p2.y);
        return Math.abs(c) / 2.0;
    }
}