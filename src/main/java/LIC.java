public class LIC {

    public static boolean LIC0(int numPoints, Point[] points, Parameters p) {
        if (p.LENGTH1 < 0) return false;
        for (int i = 0; i < numPoints - 1; i++) {
            if (Point.distance(points[i], points[i + 1]) > p.LENGTH1)
                return true;
        }
        return false;
    }

    public static boolean LIC1(int numPoints, Point[] points, Parameters p) {
        if (numPoints < 3 || p.RADIUS1 < 0)
            return false;
        for (int i = 0; i < numPoints - 2; i++) {
            if (Point.circleRadius(points[i], points[i + 1], points[i + 2]) > p.RADIUS1)
                return true;
        }
        return false;
    }

    public static boolean LIC2(int numPoints, Point[] points, Parameters p) {
        double PI = 3.14159;
        if (!(0 <= p.EPSILON && p.EPSILON < PI)) {
            return false;
        }
        if (numPoints == 2) {
            return false;
        }
        for (int i = 0; i < numPoints - 2; i++) {
            Point p1 = points[i];
            Point p2 = points[i + 1];
            Point p3 = points[i + 2];
            if (!((p1.x == p2.x && p1.y == p2.y) || (p3.x == p2.x && p3.y == p2.y))) {
                double angle = Point.angle(p1, p2, p3);
                if (angle < PI - p.EPSILON || angle > PI + p.EPSILON) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean LIC6(int numPoints, Point[] points, Parameters p) {
        if (numPoints < 3 || p.N_PTS < 3 || p.DIST < 0 || numPoints < p.N_PTS ||
            p.DIST < 0) return false;

        for (int i = 0; i <= numPoints - p.N_PTS; i++) {
            Point p_start = points[i];
            Point p_end = points[i + p.N_PTS - 1];

            for (int j = i + 1; j <= i + p.N_PTS - 2; j++) {
                Point p_mid = points[j];
                if (Point.distancePointToLine(p_start, p_end, p_mid) > p.DIST) {
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

    public static boolean LIC7(int numPoints, Point[] points, Parameters p) {
        if (numPoints < 3 || p.K_PTS < 1 || p.K_PTS > numPoints - 2 || p.LENGTH1 < 0) return false;

        for (int i = 0; i < numPoints - p.K_PTS - 1; i++) {
            if (Point.distance(points[i], points[i + p.K_PTS + 1]) > p.LENGTH1)
                return true;
        }
        return false;
    }

    public static boolean LIC8(int numPoints, Point[] points, Parameters p) {
        if (p.A_PTS < 1 || p.B_PTS < 1 || numPoints < 5 || p.A_PTS+p.B_PTS > (numPoints-3)){
            return false;
        }
        for (int i = 0; i + p.A_PTS+p.B_PTS < numPoints-2; i++){
            Point p1 = points[i];
            Point p2 = points[i+p.A_PTS+1];
            Point p3 = points[i+p.A_PTS+p.B_PTS+2];
            if (Point.circleRadius(p1, p2, p3) > p.RADIUS1){
                return true;
            }
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

    public static boolean LIC10(int numPoints, Point[] points, Parameters p) {
        if (numPoints < 5 || p.E_PTS < 1 ||p.F_PTS < 1 || p.E_PTS + p.F_PTS > numPoints - 3) return false;

        for (int i = 0; i < numPoints - p.F_PTS - p.E_PTS - 2; i ++) {
            Point p1 = points[i];
            Point p2 = points[i + p.E_PTS + 1];
            Point p3 = points[i + p.E_PTS + p.F_PTS + 2];
            
            if (Point.triangleArea(p1, p2, p3) > p.AREA1) {
                return true;
            }
        }
        return false;
    }

    public static boolean LIC11(int numPoints, Point[] points , Parameters p){
        if (numPoints < 3 || 1 > p.G_PTS || p.G_PTS > numPoints-2){
            return false;
        }
        for (int i = 0; i + p.G_PTS < numPoints-1; i++){
            Point p1 = points[i];
            Point p2 = points[i+p.G_PTS+1];
            if (p2.x - p1.x < 0){
                return true; 
            }
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


    public static boolean LIC13(int numPoints, Point[] points, Parameters p) {
        if (numPoints < 5 || p.RADIUS2 < 0 || p.RADIUS1 < 0 || p.A_PTS < 1 || p.B_PTS < 1) return false;
        
        boolean not_contained_flag = false, contained_flag = false;
        for (int i = 0; i < numPoints - p.A_PTS - p.B_PTS - 2; i++) {
            double radius = Point.circleRadius(points[i], points[i + p.A_PTS + 1], points[i + p.A_PTS + p.B_PTS + 2]);
            
            if (radius > p.RADIUS1) not_contained_flag = true;
            if (radius <= p.RADIUS2) contained_flag = true;

            if (not_contained_flag && contained_flag) return true;
        }
        return false;
    }
}
