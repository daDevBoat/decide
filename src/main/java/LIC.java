public class LIC { 
    
    public static boolean LIC0(int numPoints, Point[] points, double LENGTH1) {
        for (int i = 0; i < numPoints - 1; i++) {
            if (Point.distance(points[i], points[i+1]) > LENGTH1) return true;
        }
        return false;
    }
    
    public static boolean LIC1(int numPoints, Point[] points, double RADIUS1) {
        for (int i = 0; i < numPoints - 2; i++) {
            if (Point.distance(points[i], points[i + 1]) >= RADIUS1 * 2) continue;
            if (Point.distance(points[i], points[i + 2]) >= RADIUS1 * 2) continue;
            if (Point.distance(points[i + 1], points[i + 2]) >= RADIUS1 * 2) continue;
            return true;
        }
        return false;
    }

}
