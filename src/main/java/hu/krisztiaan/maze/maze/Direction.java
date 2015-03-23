package hu.krisztiaan.maze.maze;

import java.awt.*;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public Point destinationFromPoint(Point origin) {
        Point destination = origin;
        switch (this) {
            case NORTH: return new Point(origin.x, origin.y+1);
            case EAST: return new Point(origin.x+1, origin.y);
            case SOUTH: return new Point(origin.x, origin.y-1);
            case WEST: return new Point(origin.x-1, origin.y);
        }
        return destination;
    }
}
