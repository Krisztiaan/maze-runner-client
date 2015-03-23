package hu.krisztiaan.maze.runner;

import hu.krisztiaan.maze.maze.Direction;
import hu.krisztiaan.maze.maze.MazeMap;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        log.log(Level.FINE, "Is " +
                origin.toString() +
                " a valid field: " +
                (mMazeMap.isValidTarget(origin)));

        if(!mMazeMap.isValidTarget(origin)) return false;

        if(mMazeMap.isFinish(origin)) {
            mMazeMap.markTraversed(origin);
            log.log(Level.FINE, "Mark " + origin.toString() + " as solution.");
            return true;
        } else {
            mMazeMap.markSolution(origin);
            log.log(Level.FINE, "Mark " + origin.toString() + " as traversed.");
        }

        for(Direction direction : Direction.values()) {
            mMazeMap.reveal(origin, direction);
            log.log(Level.FINE, "Is " +
                    direction.destinationFromPoint(origin) +
                    " a valid field: "+
                    (mMazeMap.isValidTarget(direction.destinationFromPoint(origin))));

            if(run(direction.destinationFromPoint(origin))) {
                return true;
            }
        }
        return false;
    }
}