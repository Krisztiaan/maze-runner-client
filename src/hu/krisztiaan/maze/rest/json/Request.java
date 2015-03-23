package hu.krisztiaan.maze.rest.json;

import hu.krisztiaan.maze.maze.Direction;

import java.awt.*;

public class Request {

    public static String mazes() {
        return "/mazes";
    }

    public static String start(String mazeId) {
        return "/mazes/" + mazeId + "/position/start";
    }

    public static String move(Point from, Direction direction) {
        return  "{ \"from\": { \"x\": " + from.x + ", \"y\": " + from.y + "}, \"direction\": \"" + direction.toString() +" }";
    }
}
