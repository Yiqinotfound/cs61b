public class NBody {

    public static double readRadius(String Filename) {
        In in = new In(Filename);
        int n = in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String Filename) {
        In in = new In(Filename);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] Planets = new Planet[num];

        for (int i = 0; i < num; i = i + 1) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            Planets[i] = new Planet(xP, yP, xV, yV, m, img);

        }
        return Planets;
    }

    public static void main(String args[]) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] Planets = readPlanets(filename);
        double universe_radius = readRadius(filename);

        String imageToDraw = "images/starfield.jpg";
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(512, 512);
        StdDraw.setScale(-universe_radius, universe_radius);
        StdDraw.picture(0, 0, imageToDraw, 2 * universe_radius, 2 * universe_radius);

        StdDraw.show();
        StdDraw.pause(10000);

    }

}
