
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

    public static double angle(Point p1, Point p2, Point p3) {
        //Uses the dot product method
        // a = p1 - p2, b = p3 - p2 
        Point a = new Point(p1.x-p2.x, p1.y-p2.y);
        Point b = new Point(p3.x-p2.x, p3.y-p2.y);
        Point origo = new Point(0,0);
        return Math.acos( (a.x*b.x+a.y*b.y)/(distance(a,origo)*distance(b,origo)) );
    }

    // Formula retrieved from: https://math.stackexchange.com/questions/213658/get-the-equation-of-a-circle-when-given-3-points
    public static double circleRadius(Point p1, Point p2, Point p3) {
        if (Math.sin(angle(p2, p1, p3)) < 0.0001) return 0; 
        return distance(p2, p3) / (2 * Math.sin(angle(p2, p1, p3)));
    }
}