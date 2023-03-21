package byog.lab5;
import java.awt.Color;
import edu.princeton.cs.algs4.RandomSeq;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    public static void main(String[] args) {
        int WIDTH = 80;
        int HEIGHT = 80;

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        initializeWorld(world);

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        ter.renderFrame(world);
    }
    private static Random random = new Random();
    private static boolean isTileEmpty(Position p,TETile[][] world) {
        return world[p.x][p.y] == Tileset.GRASS;
    }

    private static boolean positionOutOfRange(Hex hex, TETile[][] world) {
        if (hex.getXLeft() < 0 || hex.getXRight() >= world.length || hex.getYDown() < 0 || hex.getYUp() >=world[0].length){
            return true;
        } else {
            return false;
        }
    }
    private static TETile randomColorTile() {
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);

        TETile tile = TETile.colorVariant(Tileset.FLOWER,r, g, b, random);
        return tile;
    }
    private static void initializeWorld(TETile[][] world) {
        for (int x = 0; x < world.length; x++) {
            for (int y = 0; y < world[0].length; y++) {
                world[x][y] = Tileset.GRASS;
            }
        }

        int size = 5;
        TETile randomTile = randomColorTile();
        int x = world.length / 2 ;
        int y = world[0].length / 2 ;

        Position p = new Position(x, y);
        Hex originHex = new Hex(size,p,randomTile);

        addRandomHex(originHex, world);


    }

    private static void addRandomHex(Hex hex,TETile[][] world) {
        if (positionOutOfRange(hex,world) || !isTileEmpty(hex.p, world)) {
            return;
        } else {
            hex.addHexagon(world);
            for (int i = 0; i < 6; i += 1) {
                TETile randomTile = randomColorTile();
                Hex newHex = new Hex(hex.size, hex.neighbours[i],randomTile);
                addRandomHex(newHex,world);
            }
        }

    }
}
