package byog.Core;

public class Position {
    public int x;
    public int y;
    public Position(int xx , int yy ) {
        x = xx;
        y = yy;
    }

    public Position() {
        x = 0;
        y = 0;
    }

    public static boolean equals(Position p1, Position p2) {
        return p1.x == p2.x && p1.y == p2.y;
    }

    public static int distanceSquare(Position p1, Position p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

}
