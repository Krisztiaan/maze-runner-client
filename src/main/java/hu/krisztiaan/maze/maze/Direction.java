package hu.krisztiaan.maze.maze;

import java.awt.*;

/**
 * Created by Krisz on 2015.03.23..
 */
public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public Point destinationFromPoint(Point origin) {
        Point destination = origin;
        switch (this) {
            case NORTH: destination.y++;
                break;
            case EAST: destination.x++;
                break;
            case SOUTH: destination.y--;
                break;
            case WEST: destination.x--;
                break;
        }
        return destination;
    }

    public Direction nextDirection() {
        switch (this) {
            case NORTH: return EAST;
            case EAST: return SOUTH;
            case SOUTH: return WEST;
            case WEST: return NORTH;
        }
        return NORTH;
    }
}
