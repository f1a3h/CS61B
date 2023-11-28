public class NBody {
    public static double readRadius(String path) {
        In in = new In(path);
        int n = in.readInt();

        return in.readDouble();
    }

    public static Planet[] readPlanets(String path) {
        In in = new In(path);
        int n = in.readInt();
        double r = in.readDouble();

        Planet[] ps = new Planet[n];
        for (int i = 0; i < n; ++i) {
            ps[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
        }

        return ps;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        /* Draw the background. */
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");

        /* Draw all of the planets. */
        for (Planet p: planets) {
            p.draw();
        }

        /* Create animation. */
        StdDraw.enableDoubleBuffering();

        double time = 0;
        int num = planets.length;
        while (T - time > 1e-5) {
            double[] xForces = new double[num];
            double[] yForces = new double[num];
            for (int i = 0; i < num; ++i) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < num; ++i) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.setScale(-radius, radius);
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (Planet p: planets) {
                p.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);

            time += dt;
        }

        /* Print the universe. */
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        Planet.print(planets);
    }
}
