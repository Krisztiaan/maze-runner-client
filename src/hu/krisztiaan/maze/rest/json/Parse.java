package hu.krisztiaan.maze.rest.json;

import hu.krisztiaan.maze.maze.FieldType;
import hu.krisztiaan.maze.maze.MazeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.util.ArrayList;

public class Parse {

    public static ArrayList<MazeMap> mazes(String source) throws JSONException{
        ArrayList<MazeMap> collectedMazes = new ArrayList<MazeMap>();
        JSONObject obj = new JSONObject(source);
        JSONArray arr = obj.getJSONArray("mazesUrl");

        for(int i=0;i<arr.length();i++){
            collectedMazes.add(new MazeMap(arr.getJSONObject(i).getString("code")));
        }

        return collectedMazes;
    }

    public static Point start(String source) throws JSONException {
        JSONObject obj = new JSONObject(source);
        return new Point(obj.getInt("x"), obj.getInt("y"));
    }

    public static FieldType moveResult(String source) throws JSONException {
        JSONObject obj = new JSONObject(source);
        return obj.getString("field")=="."
                ? FieldType.ROAD : FieldType.FINISH;
    }

}
