public class Planet {
    public double xxPos, yyPos;
    public double xxVel, yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        return Math.sqrt(Math.pow(p.xxPos - xxPos, 2) + Math.pow(p.yyPos - yyPos, 2));
    }

    public static final double G = 6.67e-11;

    public double calcForceExertedBy(Planet p) {
        return G * p.mass * mass / (calcDistance(p) * calcDistance(p));
    }

    public double calcForceExertedByX(Planet p) {
        return calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        return calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        int len = allPlanets.length;
        double Fx = 0;
        for (int i = 0; i < len; i = i + 1) {
            if (!this.equals(allPlanets[i])) {
                Fx += calcForceExertedByX(allPlanets[i]);
            }
        }
        return Fx;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        int len = allPlanets.length;
        double Fy = 0;
        for (int i = 0; i < len; i = i + 1) {
            if (!this.equals(allPlanets[i])) {
                Fy += calcForceExertedByY(allPlanets[i]);
            }
        }
        return Fy;
    }

    public void update(double time, double Fx, double Fy) {
        double ax = Fx / mass;
        double ay = Fy / mass;
        xxVel = xxVel + ax * time;
        yyVel = yyVel + ay * time;
        xxPos = xxPos + xxVel * time;
        yyPos = yyPos + yyVel * time;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, this.imgFileName);
    }

}
