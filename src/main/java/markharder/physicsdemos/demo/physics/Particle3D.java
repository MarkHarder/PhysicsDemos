package markharder.physicsdemos.demo.physics;

import java.awt.Color;
import java.awt.Graphics;

/**
 * A particle in three dimensions
 *
 * @author Mark Harder
 * @version 1.0
 */
public class Particle3D {
    private double x, y, z, vx, vy, vz, ay;

    public Particle3D(double x, double y, double z, double vx, double vy, double vz) {
        // set position
        this.x = x;
        this.y = y;
        this.z = z;

        // set velocities
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;

        // set y-acceleration
        this.ay = -9.8 / 2 / 60;
    }

    public void tick() {
        x += vx;
        y += vy;
        z += vz;

        vy += ay;


        if (z < 0) {
            z = 0;
        }
    }

    public void draw(Graphics g, int width, int height) {
        g.setColor(Color.BLUE);
        g.fillOval((int) (x - z / 2), (int) (height - (y - z / 2)), (int) z, (int) z);
    }
}
