package hu.krisztiaan.maze;

import hu.krisztiaan.maze.maze.MazeMap;
import hu.krisztiaan.maze.maze.MazeServer;
import hu.krisztiaan.maze.runner.MazeRunner;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger log = Logger.getLogger(MazeServer.class.getName());

    public static void main(String[] args) {

        MazeMap myMap;
        log.log(Level.FINE, "'morning!");
        MazeServer.serverAddress = args[0];
        log.log(Level.FINE, MazeServer.serverAddress);
        myMap = new MazeMap(args[1]);
        myMap.printMap();
        System.out.println("It's a great day for a run! Let's send Joe to " + args[1]);
        MazeRunner runnerJoe = new MazeRunner(myMap);
        runnerJoe.findExit().printMap();
    }
}
