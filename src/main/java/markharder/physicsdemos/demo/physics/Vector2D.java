package markharder.physicsdemos.demo.physics;

/**
 * A vector class
 *
 * @author Mark Harder
 * @version 1.0
 */
public class Vector2D {
    private double x, y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double dot(Vector2D v1, Vector2D v2) {
        return v1.x * v2.x + v1.y * v2.y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
