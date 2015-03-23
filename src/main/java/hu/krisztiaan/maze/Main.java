package hu.krisztiaan.maze;

import hu.krisztiaan.maze.maze.MazeMap;
import hu.krisztiaan.maze.maze.MazeServer;
import hu.krisztiaan.maze.runner.MazeRunner;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger log = Logger.getLogger(MazeServer.class.getName());

    public static void main(String[] args) {

        String[] argse = new String[2];
        argse[0] = "http://localhost:8080";
        argse[1] = "maze-1";

        MazeMap myMap;
        // write your code here
        log.log(Level.INFO, "'morning!");
        MazeServer.serverAddress = argse[0];
        log.log(Level.INFO, MazeServer.serverAddress);
        myMap = new MazeMap(argse[1]);
        myMap.printMap();
        System.out.print("It's a great day for a run! Let's send Joe to " + argse[1]);
        MazeRunner runnerJoe = new MazeRunner(myMap);
        runnerJoe.findExit().printMap();
    }
}
