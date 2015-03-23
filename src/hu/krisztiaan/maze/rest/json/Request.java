package hu.krisztiaan.maze.rest.json;

import hu.krisztiaan.maze.maze.Direction;

import java.awt.*;

public class Request {

    public static String mazesUrl() {
        return "/mazesUrl";
    }

    public static String startUrl(String mazeId) {
        return "/mazes/" + mazeId + "/position/start";
    }

    public static String moveUrl(String mazeId) {
        return "/mazes/" + mazeId + "/position";
    }

    public static String move(Point from, Direction direction) {
        return  "{ \"from\": { \"x\": " + from.x + ", \"y\": " + from.y + "}, \"direction\": \"" + direction.toString() +" }";
    }
}
