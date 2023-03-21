package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class HallWay {

    private static int minIndex(int [] a) {
        int index = 0;
        for (int i = 0; i < a.length; i += 1) {
            if (a[i] < a[index]) {
                index = i;
            }
        }
        return index;
    }

    private static void drawX(TETile[][] world,Position start, Position end ,TETile tile) {
        if (end.x >= start.x) {
            for (int x = start.x; x <= end.x; x += 1) {
                world[x][start.y] = tile;
            }
        } else {
            for (int x = start.x; x >= end.x; x -= 1) {
                world[x][start.y] = tile;
            }
        }
    }
    private static Position[] getConerPositons(Room r1, Room r2) {
        Position[] cornerPosition = new Position[8];
        for (int i = 0; i < 2 ; i += 1) {
            for (int j = 0; j < 4; j += 1) {
                if (i == 0) {
                    cornerPosition[j + 4 * i].x = r1.p.x + (j % 2) *(r1.width - 1);
                    cornerPosition[j + 4 * i].y = r1.p.y + (j / 2) * (r1.height - 1);
                } else {
                    cornerPosition[j + 4 * i].x = r2.p.x + (j % 2) *(r2.width - 1);
                    cornerPosition[j + 4 * i].y = r2.p.y + (j / 2) * (r2.height - 1);
                }
            }
        }
        return cornerPosition;
    }
    public static Position[] hallWayGenerator(Room r1, Room r2) {
        Position[] results = new Position[3];
        Position turning = new Position();
        Position [] border = new Position[4];
        int leftx = Math.min(r1.p.x, r2.p.x);
        int downy = Math.min(r1.p.y, r2.p.y);
        int rightx = Math.max(r1.p.x + r1.width -1, r2.p.x + r2.width - 1);
        int upy = Math.max(r1.p.y + r1.height - 1, r2.p.y + r2.height - 1);
        border[0] = new Position(leftx, downy);
        border[1] = new Position(leftx, upy);
        border[2] = new Position(rightx, downy);
        border[3] = new Position(rightx, upy);

        Position[] cornerPositions = getConerPositons(r1, r2);

        tag: for (int i = 0; i < 4; i += 1) {
            for (int j = 0; j < 8; j += 1) {
                if ((border[i].x == cornerPositions[j].x) && (border[i].y == cornerPositions[j].y)) {
                    continue tag;
                }
                turning.x = border[i].x;
                turning.y = border[i].y;
            }
        }

        Position connect1, connect2;
        int [] squareDistances = new int [4];
        for (int j = 0; j < 4; j += 1) {
            squareDistances[j] = Position.distanceSquare(turning,cornerPositions[j]);
        }
        int temp = 0;
        for (int j = 0; j < 4; j += 1) {
           if (squareDistances[j] < squareDistances[temp]) {
               temp = j;
           }
        }
        connect1 = new Position(cornerPositions[temp].x,cornerPositions[temp].y);

        for (int j = 4; j < 8; j += 1) {
            if (squareDistances[j % 4] < squareDistances[temp % 4]) {
                temp = j;
            }
        }

        connect2 = new Position(cornerPositions[temp].x,cornerPositions[temp].y);

        results[0] = turning;
        results[1] = connect1;
        results[2] = connect2;

        return  results;

    }

    public static void drawHallway(TETile[][] world, Position[] points) {
       Position turning = points[0];
       Position connect1 = points[1];
       Position connect2 = points[2];


    }


}
