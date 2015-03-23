package hu.krisztiaan.maze.maze;

import hu.krisztiaan.maze.rest.GetData;
import hu.krisztiaan.maze.rest.HttpResponseException;
import hu.krisztiaan.maze.rest.Server;
import hu.krisztiaan.maze.rest.json.Parse;
import hu.krisztiaan.maze.rest.json.Request;
import org.json.JSONException;
import sun.plugin.dom.exception.InvalidStateException;

import java.awt.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.MissingResourceException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Krisz on 2015.03.23..
 */
public class MazeServer {
    private static final Logger log = Logger.getLogger(MazeServer.class.getName());

    public static String serverAddress;

    private static void checkServerAddress() {

        if(serverAddress == null) throw new InvalidStateException("No URL was given!");

        if(serverAddress.charAt(serverAddress.length()-1) == '/') {
            serverAddress = serverAddress.substring(0, serverAddress.length()-1);
        }
    }

    public static ArrayList<MazeMap> getAvailableMazes() throws MalformedURLException {
        checkServerAddress();
        try {
        return Parse.mazes(Server.GetData(serverAddress, Request.mazes()));
        } catch (HttpResponseException e) {
            log.log(Level.INFO, "This should not really happen");
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static Point getMazeStart(String mazeId) throws MalformedURLException{
        checkServerAddress();
        try {return Parse.start(Server.GetData(serverAddress, Request.start(mazeId)));}
        catch (HttpResponseException e) {
            throw new MissingResourceException("The " + mazeId + " map does not exist.", "MazeServer", mazeId);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getMazeWidth(String mazeId) throws MalformedURLException {
        checkServerAddress();
        for(MazeMap currentMaze : getAvailableMazes()) {
            if(currentMaze.getId() == mazeId) return currentMaze.getHeight();
        }
        return 0;
    }

    public static int getMazeHeight(String mazeId) throws MalformedURLException {
        checkServerAddress();
        for(MazeMap currentMaze : getAvailableMazes()) {
            if(currentMaze.getId() == mazeId) return currentMaze.getWidth();
        }
        return 0;
    }

    public static FieldType getMoveResult(String mazeId, Point origin, Direction direction) {
        try {
            Parse.moveResult(Server.PostData(serverAddress + "/mazes/" + mazeId + "/position", Request.move(origin, direction)));
        }
    }
}
