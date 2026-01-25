import static java.lang.Math.PI;
import static java.lang.Math.max;

public class Point {
    double x;
    double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    public static double triangleArea(Point p1, Point p2, Point p3) {
        double c = p1.x * (p2.y - p3.y) + p2.x * (p3.y - p1.y) + p3.x * (p1.y - p2.y);
        return Math.abs(c) / 2.0;
    }

    public static double angle(Point p1, Point p2, Point p3) {
        // Uses the dot product method
        // a = p1 - p2, b = p3 - p2
        Point a = new Point(p1.x - p2.x, p1.y - p2.y);
        Point b = new Point(p3.x - p2.x, p3.y - p2.y);
        Point origo = new Point(0, 0);
        return Math.acos((a.x * b.x + a.y * b.y) / (distance(a, origo) * distance(b, origo)));
    }

    // Formula retrieved from:
    // https://math.stackexchange.com/questions/213658/get-the-equation-of-a-circle-when-given-3-points
    public static double circleRadius(Point p1, Point p2, Point p3) {
        double max_angle = max(Point.angle(p1, p2, p3), Point.angle(p3, p1, p2));
        max_angle = max(max_angle, Point.angle(p2, p3, p1));

        if (max_angle < PI / 2) {
            return distance(p2, p3) / (2 * Math.sin(angle(p2, p1, p3)));
        } else {
            double max_distance = max(Point.distance(p1, p2), Point.distance(p1, p3));
            max_distance = max(max_distance, Point.distance(p2, p3));

            return max_distance / 2;
        }

    }

    public static double directedAngle(Point p1, Point p2, Point p3) {
        // a = p1 - p2, b = p3 - p2;
        double ax = p1.x - p2.x;
        double ay = p1.y - p2.y;
        double bx = p3.x - p2.x;
        double by = p3.y - p2.y;

        double dotProduct = ax * bx + ay * by;
        double crossProduct = ax * by - ay * bx;

        double angle = Math.atan2(crossProduct, dotProduct); // (-pi, pi]
        if (angle < 0)
            angle += 2 * Math.PI; // [0, 2pi)

        return angle;
    }

    // Formular retrieved from:
    // https://brilliant.org/wiki/dot-product-distance-between-point-and-a-line/
    public static double distancePointToLine(Point p_start, Point p_end, Point p_mid) {
        if (p_start.x == p_end.x && p_start.y == p_end.y) {
            return distance(p_start, p_mid);
        }
        double a = p_end.y - p_start.y;
        double b = p_start.x - p_end.x;
        double c = p_end.x * p_start.y - p_start.x * p_end.y;
        return Math.abs(a * p_mid.x + b * p_mid.y + c) / Math.sqrt(a * a + b * b);
    }
}