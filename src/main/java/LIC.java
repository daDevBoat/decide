public class LIC { 
    
    public static boolean LIC0(int numPoints, Point[] points, double LENGTH1) {
        for (int i = 0; i < numPoints - 1; i++) {
            if (Point.distance(points[i], points[i+1]) > LENGTH1) return true;
        }
        return false;
    }
    
    public static boolean LIC1(int numPoints, Point[] points, double RADIUS1) {
        if (numPoints < 3 || RADIUS1 < 0) return false;
        for (int i = 0; i < numPoints - 2; i++) {
            if (Point.circleRadius(points[i], points[i + 1], points[i + 2]) > RADIUS1) return true;
        }
        return false;
    }

    public static boolean LIC2(int numPoints, Point[] points, double EPSILON) {
        double PI = 3.14159;
        if (!(0 <= EPSILON && EPSILON < PI)){
            return false;
        }
        if (numPoints == 2){
            return false;
        }
        for (int i = 0; i<numPoints-2 ; i++){
            Point p1 = points[i];
            Point p2 = points[i+1];
            Point p3 = points[i+2];
            if (! ( (p1.x == p2.x && p1.y == p2.y) || (p3.x == p2.x && p3.y == p2.y) ) ) {
                double angle = Point.angle(p1, p2, p3);
                if (angle < PI-EPSILON || angle > PI+EPSILON){
                    return true; 
                }
            }
        }
        return false;
    }

    public static boolean LIC3(int numPoints, Point[] points, Parameters p) {
        if (p.AREA1 < 0) return false;
        if(numPoints == 2) return false;
        for (int i = 0; i < numPoints - 2; i++){
            if(Point.triangleArea(points[i], points[i+1], points[i+2]) > p.AREA1){
                return true;
            }
        }
        return false;
    }

    public static boolean LIC7(int numPoints, Point[] points, double LENGTH1, int K_PTS) {
        if (numPoints < 3 || K_PTS < 1 || K_PTS > numPoints - 2) return false;
        for (int i = 0; i < numPoints - K_PTS - 1; i++) {
            if (Point.distance(points[i], points[i + K_PTS + 1]) > LENGTH1) return true;
        }
        return false;
    }

    public static boolean LIC9(int numPoints, Point[] points, Parameters p){
        if(numPoints < 5) return false;
        if(p.C_PTS < 1 || p.D_PTS < 1) return false;
        if(p.C_PTS + p.D_PTS > numPoints - 3) return false;

        for(int i = 0; i + p.C_PTS + p.D_PTS + 2 < numPoints; i++){

            Point a = points[i];
            Point b = points[i + p.C_PTS + 1];
            Point c = points[i + p.C_PTS + p.D_PTS + 2];

            /* If either the first point or the last point (or both) coincide with the vertex, 
            the angle is undefined and the LIC is not satisfied by those three points. */
            if((a.x == b.x && a.y == b.y) || (c.x == b.x && c.y == b.y)) continue;

            double ang = Point.directedAngle(a, b, c);

            if((ang < Math.PI - p.EPSILON) || (ang > Math.PI + p.EPSILON)) return true;
        }

        return false;
    }

    public static boolean LIC12(int numPoints, Point[] points , Parameters p){
        if(numPoints < 3) return false;
        if(p.LENGTH2 < 0) return false; 

        boolean condA = false; /* Exists pair with distance > LENGTH1 */
        boolean condB = false; /* Exists pair with distance < LENGTH2 */

        for(int i = 0; i + p.K_PTS + 1 < numPoints; i++){

            double dist = Point.distance(points[i], points[i + p.K_PTS + 1]);

            if(dist > p.LENGTH1) condA = true;
            if(dist < p.LENGTH2) condB = true;

            if(condA && condB) return true;
        }

        return false;
    }

}
