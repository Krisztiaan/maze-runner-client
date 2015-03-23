package hu.krisztiaan.maze.maze;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by Krisz on 2015.03.23..
 */
public class MazeMap {
    private String mMazeId = "";

    // The first dimension is the rows, second is the columns.
    private FieldType[][] mField;

    public MazeMap(String mazeId) {
        mMazeId = mazeId;
        mField = new FieldType[MazeServer.getMazeWidth(mMazeId)]
                [MazeServer.getMazeHeight(mMazeId)];

        Point startingPoint= MazeServer.getMazeStart(mMazeId);

        /*
         * At this point, we can get the @FieldType.START point.
         * Everything else is @FieldType.UNKNOWN
         */
        Arrays.fill(mField, FieldType.UNKNOWN);
        mField[startingPoint.x][startingPoint.y] = FieldType.START;
    }

    public String getId() {
        return mMazeId;
    }

    public int getWidth() {
        if(mField!=null) return mField[0].length;
        else return 0;
    }

    public int getHeight() {
        if(mField!=null) return mField.length;
        else return 0;
    }

    public FieldType move(Point origin, Direction direction) {
        return MazeServer.getMoveResult(mMazeId, origin, direction);
    }

    public void printMap() {
        System.out.println("\nPrinting map (" + mMazeId + ")\n");
        for (int i = 0; i < mField.length; i++) {
            for (int j = 0; j < mField.length; j++) {
                System.out.print(mField[i][j].toString());
            }
            System.out.println();
        }
        System.out.println("\nDone with map. (" + mMazeId + ")\n");
    }

}
