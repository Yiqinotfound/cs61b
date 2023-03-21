package byog.lab5;

import byog.Core.Position;
import byog.TileEngine.TETile;

public class Hex {
    public int size;
    public Position p;


    private TETile tile;

    public Position[] neighbours;
    public Hex(int size, Position p ,TETile tile) {
        this.size = size;
        this.p = p;
        this.tile = tile;
        neighbours = new Position[6];
        neighbours[0] = new Position(this.p.x + (this.size - 1) * 2 + 1, this.size + this.p.y); //右上
        neighbours[1] = new Position(p.x +2 * size - 1, p.y - size); //右下
        neighbours[2] = new Position(p.x - (2 * size - 1), p.y + size); //左上
        neighbours[3] = new Position(p.x - (2 * size - 1), p.y - size);//左下
        neighbours[4] = new Position(p.x, p.y + 2 * size);//上
        neighbours[5] = new Position(p.x, p.y - 2 * size);//下
    }


    public int getXRight() {
        return p.x + 2 * (size - 1);
    }

    public int getXLeft() {
        return p.x - (size - 1);
    }

    public int getYUp() {
        return p.y + 2 * size - 1;
    }

    public int getYDown() {
        return p.y;
    }
    private static int yRow(int size,int row, Position p) {
        return p.y + (row - 1);
    }

    private static int lengthOfRow(int size, int row) {
        if (row <= size) {
            return size + 2 * (row - 1);
        } else {
            return size + 2 *(size - 1) - 2 * (row - size - 1);
        }
    }
    private static int xBegin(int size, int row, Position p) {
        if (row <= size) {
            return p.x - (row - 1);
        } else {
            return p.x -(size - 1) + (row - size - 1);
        }
    }

    public void addHexagon(TETile[][] world) {
        for(int row = 1; row <= size * 2; row += 1) {
            int length = lengthOfRow(size, row);
            int xBegin = xBegin(size,row,p);
            int y = yRow(size, row, p);
            for (int x = xBegin; x <= xBegin + length - 1; x += 1) {
                world[x][y] = tile;
            }
        }

    }
}
