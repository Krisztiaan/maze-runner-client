package hu.krisztiaan.maze.maze;

import hu.krisztiaan.maze.rest.HttpResponseException;
import hu.krisztiaan.maze.rest.Server;
import hu.krisztiaan.maze.rest.json.Parse;
import hu.krisztiaan.maze.rest.json.Request;
import netscape.security.ForbiddenTargetException;
import org.json.JSONException;
import sun.plugin.dom.exception.InvalidStateException;

import java.awt.*;
import java.net.MalformedURLException;
import java.security.InvalidParameterException;
import java.util.MissingResourceException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MazeServer {
    private static final Logger log = Logger.getLogger(MazeServer.class.getName());

    public static String serverAddress;

    private static void checkServerAddress() {

        if (serverAddress == null) throw new InvalidStateException("No URL was given!");

        if (serverAddress.charAt(serverAddress.length() - 1) == '/') {
            serverAddress = serverAddress.substring(0, serverAddress.length() - 1);
        }
    }

//    public static ArrayList<String> getAvailableMazeIds() {
//        checkServerAddress();
//        log.log(Level.FINE, "Getting available mazes");
//        try {
//            return Parse.mazeIds(Server.GetData(serverAddress, Request.mazesUrl()));
//        } catch (HttpResponseException e) {
//            log.log(Level.FINE, "This should not really happen");
//            throw new RuntimeException(e);
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        } catch (MalformedURLException e) {
//            throw new InvalidParameterException(e.getMessage());
//        }
//    }

    public static Point getMazeStart(String mazeId) {
        checkServerAddress();
        try {
            return Parse.start(Server.GetData(serverAddress, Request.startUrl(mazeId)));
        } catch (HttpResponseException e) {
            throw new MissingResourceException("The " + mazeId + " map does not exist.", "MazeServer", mazeId);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new InvalidParameterException(e.getMessage());
        }
    }

    public static int getMazeHeight(String mazeId) {
        checkServerAddress();
        log.log(Level.FINE, "Getting " + mazeId + "'s height.");
        try {
            return Parse.height(mazeId, Server.GetData(serverAddress, Request.mazesUrl()));
        } catch (HttpResponseException e) {
            log.log(Level.FINE, "This should not really happen");
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new InvalidParameterException(e.getMessage());
        }
    }

    public static int getMazeWidth(String mazeId) {
        checkServerAddress();
        log.log(Level.FINE, "Getting " + mazeId + "'s width.");
        try {
            return Parse.width(mazeId, Server.GetData(serverAddress, Request.mazesUrl()));
        } catch (HttpResponseException e) {
            log.log(Level.FINE, "This should not really happen");
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new InvalidParameterException(e.getMessage());
        }
    }

    public static FieldType getMoveResult(String mazeId, Point origin, Direction direction) {
        try {
            return Parse.moveResult(Server.PostData(serverAddress, Request.moveUrl(mazeId), Request.move(origin, direction)));
        } catch (HttpResponseException e) {
            switch (e.getResponseCode()) {
                case 401:
                    throw new IllegalAccessError("Unauthorized.");
                case 418:
                    return FieldType.WALL;
                case 403:
                    throw new ForbiddenTargetException("Forbidden.");
                case 404:
                    throw new MissingResourceException("The " + mazeId + " map does not exist.", "MazeServer", mazeId);
                default:
                    return FieldType.UNKNOWN;
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new InvalidParameterException(e.getMessage());
        }
    }
}
