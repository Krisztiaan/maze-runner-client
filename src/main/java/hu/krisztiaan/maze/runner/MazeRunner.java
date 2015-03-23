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

    final static FieldType TRIED = FieldType.TAKEN_ROAD;
    final static FieldType PATH = FieldType.ROAD;
    final static FieldType END = FieldType.FINISH;

    MazeMap mMazeMap;
    Point currentPosition;
    ArrayList<Direction> movesMade = new ArrayList<Direction>();
    ArrayList<Direction> optimalRoute = new ArrayList<Direction>();

    public MazeRunner(MazeMap map) {
        mMazeMap = map;
    }

    public MazeMap findExit() {
        run(mMazeMap.getStart());
        return mMazeMap;
    }

    public boolean run(Point origin) {
        log.log(Level.INFO, "\n" + origin.toString() + "\n notRoad="+(!mMazeMap.isValidTarget(origin))+"\nisStart="+(mMazeMap.getStart()!=origin));
        if(!mMazeMap.isValidTarget(origin)&&mMazeMap.getStart()!=origin) return false;

        if(mMazeMap.isFinish(origin)) {
            mMazeMap.markSolution(origin);
            return true;
        } else {
            mMazeMap.markTraversed(origin);
        }

        for(Direction direction : Direction.values()) {
            mMazeMap.reveal(origin, direction);
            if(run(direction.destinationFromPoint(origin))) {
                mMazeMap.markSolution(direction.destinationFromPoint(origin));
                return true;
            }
        }

        return false;
    }
}