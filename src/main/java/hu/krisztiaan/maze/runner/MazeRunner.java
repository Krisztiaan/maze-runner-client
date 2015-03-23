package hu.krisztiaan.maze.runner;

import hu.krisztiaan.maze.maze.Direction;
import hu.krisztiaan.maze.maze.FieldType;
import hu.krisztiaan.maze.maze.MazeMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Krisz on 2015.03.23..
 */
public class MazeRunner {
    private static final Logger log = Logger.getLogger(MazeMap.class.getName());

    public MazeMap mMazeMap;
    public MazeRunner(MazeMap map) {
        mMazeMap = map;
    }

    public MazeMap findExit() {
        run(mMazeMap.getStart());
        return mMazeMap;
    }

    public boolean run(Point origin) {
        log.log(Level.INFO, "Is " +
                origin.toString() +
                " a valid field: " +
                (mMazeMap.isValidTarget(origin)));

        if(!mMazeMap.isValidTarget(origin)) return false;

        if(mMazeMap.isFinish(origin)) {
            mMazeMap.markSolution(origin);
            return true;
        } else {
            mMazeMap.markTraversed(origin);
        }

        for(Direction direction : Direction.values()) {
            mMazeMap.reveal(origin, direction);
            log.log(Level.INFO, "Is " +
                    direction.destinationFromPoint(origin) +
                    " a valid field: "+
                    (mMazeMap.isValidTarget(direction.destinationFromPoint(origin))));

            if(run(direction.destinationFromPoint(origin))) {
                mMazeMap.markSolution(direction.destinationFromPoint(origin));
                return true;
            }
        }
        return false;
    }
}