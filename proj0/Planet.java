public class Planet {
    private static final double G = 6.67e-11;

    private double xxPos, yyPos;
    private double xxVel, yyVel;
    private double mass;
    private String imgFileName;

    /**
     * Construct by assigning values.
     */
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /**
     * Construct by copying an existing planet.
     */
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double ddx = (xxPos - p.xxPos) * (xxPos - p.xxPos);
        double ddy = (yyPos - p.yyPos) * (yyPos - p.yyPos);
        return Math.sqrt(ddx + ddy);
    }

    public double calcForceExertedBy(Planet p) {
        double dist = calcDistance(p);
        return G * mass * p.mass / (dist * dist);
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - xxPos;
        double dist = calcDistance(p);
        double force = calcForceExertedBy(p);
        return force * dx / dist;
    }

    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - yyPos;
        double dist = calcDistance(p);
        double force = calcForceExertedBy(p);
        return force * dy / dist;
    }

    public double calcNetForceExertedByX(Planet[] ps) {
        int n = ps.length;
        double total = 0;
        for (Planet p : ps) {
            if (equals(p)) continue;
            total += calcForceExertedByX(p);
        }
        return total;
    }

    public double calcNetForceExertedByY(Planet[] ps) {
        int n = ps.length;
        double total = 0;
        for (Planet p : ps) {
            if (equals(p)) continue;
            total += calcForceExertedByY(p);
        }
        return total;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel += aX * dt;
        yyVel += aY * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw() {
        String imgPath = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, imgPath);
    }

    public static void print(Planet[] planets) {
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}
