package hu.krisztiaan.maze.maze;

import hu.krisztiaan.maze.rest.GetData;
import hu.krisztiaan.maze.rest.Server;
import hu.krisztiaan.maze.rest.json.Parse;
import hu.krisztiaan.maze.rest.json.Request;
import sun.plugin.dom.exception.InvalidStateException;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Krisz on 2015.03.23..
 */
public class MazeServer {

    public static String serverAddress;

    private static void checkServerAddress() {
        if(serverAddress == null) throw new InvalidStateException("No URL was given!");
    }

    public static ArrayList<MazeMap> getAvailableMazes() {
        checkServerAddress();
        return Parse.mazes(Server.GetData(serverAddress, Request.mazes()));
    }

    public static Point getMazeStart(String mazeId) {
        checkServerAddress();
        return Server.GetData(serverAddress, Request.start(mazeId));
    }

    public static int getMazeWidth(String mazeId) {
        checkServerAddress();
        return Server.GetData(serverAddress, Request.width(mazeId));
    }

    public static int getMazeHeight(String mazeId) {
        checkServerAddress();
        for(MazeMap currentMaze : getAvailableMazes()) {
            if(currentMaze.getId() == mazeId) return currentMaze.getWidth();
        }
        return 0;
    }
}
