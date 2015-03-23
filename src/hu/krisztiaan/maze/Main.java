package hu.krisztiaan.maze;

import hu.krisztiaan.maze.maze.MazeMap;
import hu.krisztiaan.maze.maze.MazeServer;

public class Main {

    public static void main(String[] args) {
	// write your code here
        MazeServer.serverAddress = "http://localhost:8080";
        MazeMap myMaze = new MazeMap(MazeServer.getAvailableMazes().get(0).getId());
        myMaze.printMap();
    }
}
