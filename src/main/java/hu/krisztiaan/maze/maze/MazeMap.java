package hu.krisztiaan.maze.maze;

import java.awt.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Krisz on 2015.03.23..
 */
public class MazeMap {
    private static final Logger log = Logger.getLogger(MazeMap.class.getName());
    private String mMazeId = "";
    private Point mStartingPoint;

    // The first dimension is the rows, second is the columns.
    private FieldType[][] mField;

    public MazeMap(String mazeId) {
        mMazeId = mazeId;
        mField = new FieldType[MazeServer.getMazeWidth(mMazeId)]
                [MazeServer.getMazeHeight(mMazeId)];

        mStartingPoint = MazeServer.getMazeStart(mMazeId);

        /*
         * At this point, we can get the @FieldType.START point.
         * Everything else is @FieldType.UNKNOWN
         */
        for (FieldType[] tempRow : mField) Arrays.fill(tempRow, FieldType.UNKNOWN);
        mField[mStartingPoint.x][mStartingPoint.y] = FieldType.START;
    }

    public String getId() {
        return mMazeId;
    }

    public int getWidth() {
        if (mField != null) return mField[0].length;
        else return 0;
    }

    public int getHeight() {
        if (mField != null) return mField.length;
        else return 0;
    }

    public Point getStart() {
        return mStartingPoint;
    }

    public FieldType getFieldTypeAt(Point position) {
        try{
        return mField[position.x][position.y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return FieldType.WALL;
        }
    }

    public boolean isFinish(Point position) {
        return getFieldTypeAt(position) == FieldType.FINISH;
    }

    public boolean isValidTarget(Point there) {
            log.log(Level.INFO, "Is Point(" + there.x + "," + there.y + ") a Road? It's "+getFieldTypeAt(there).toString());
            return getFieldTypeAt(there) == FieldType.UNKNOWN
                    && getFieldTypeAt(there) == FieldType.ROAD;
    }

    private boolean setField(Point field, FieldType type) {
        try {
            mField[field.x][field.y] = type;
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public FieldType reveal(Point origin, Direction direction) {
        log.log(Level.INFO, "Movement from " + origin.toString() + " to direction " + direction.toString());

        if(origin.x<0||origin.y<0||origin.x>getWidth()||origin.y>getHeight()) return FieldType.WALL;
        else if(getFieldTypeAt(direction.destinationFromPoint(origin)) == FieldType.UNKNOWN) {
            setField(direction.destinationFromPoint(origin), MazeServer.getMoveResult(mMazeId, origin, direction));
        }

        return getFieldTypeAt(direction.destinationFromPoint(origin));
    }

    public boolean markTraversed(Point destination) {
        if (getFieldTypeAt(destination) == FieldType.ROAD) {
            setField(destination, FieldType.TAKEN_ROAD);
        } else if (getFieldTypeAt(destination) == FieldType.WALL) return false;
        return true;
    }

    public boolean markSolution(Point destination) {
        if (mField[destination.y][destination.x] == FieldType.ROAD
                || mField[destination.y][destination.x] == FieldType.TAKEN_ROAD) {
            mField[destination.y][destination.x] = FieldType.SOLUTION;
        } else if (mField[destination.y][destination.x] == FieldType.WALL) return false;
        return true;
    }

    public void printMap() {
        log.log(Level.FINE, "Printing map.");
        System.out.println("\nPrinting map (" + mMazeId + ")\n");
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                System.out.print(mField[i][j].toString());
            }
            System.out.println();
        }
        System.out.println("\nDone with map. (" + mMazeId + ")\n");
    }

}
