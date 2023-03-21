package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Room {
    protected int width;
    protected int height;
    protected Position p;

    public Room(int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        p = new Position(x, y);
    }

    public void drawRoom(TETile[][] world) {
        for (int x = p.x; x < p.x + width; x += 1) {
            for (int y = p.y; y < p.y + height; y += 1 ) {
                if(x == p.x || y == p.y || x == p.x + width - 1 || y == p.y + height - 1) {
                    world[x][y] = Tileset.WALL;
                } else {
                    world[x][y] = Tileset.FLOOR;
                }
            }
        }
    }

    public boolean borderCheck(int borderWidth, int borderHeight, Position p, int width, int height) {
        if (p.x < 0 || p.x + width > borderWidth - 1 || p.y < 0 || p.y + height > borderHeight - 1) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean existingCheck(List<Room> list, int x, int y, int width, int height) {
        if (list.isEmpty()) {
            return true;
        } else {
            for (int i = 0; i < list.size(); i += 1) {
                int x0 = list.get(i).p.x;
                int y0= list.get(i).p.y;
                int width0 = list.get(i).width;
                int height0 = list.get(i).height;

                Set<Integer> sets =  new HashSet<>();
                for(int k = 0; k < width0; k += 1) {
                    for (int j = 0; j < height0; j += 1) {
                        sets.add(x0 + k);
                        sets.add(y0 + j);
                    }
                }

                for (int k = 0; k < width; k += 1) {
                    for (int j = 0; j < height; j += 1) {
                        if ((sets.contains(x + k)) || sets.contains(y + j)) {
                            return false;
                        }
                    }
                }

            }
            return true;
        }
    }


}
