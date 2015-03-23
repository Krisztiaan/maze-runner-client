package hu.krisztiaan.maze.rest.json;

import hu.krisztiaan.maze.maze.FieldType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.util.ArrayList;

public class Parse {

    public static ArrayList<String> mazeIds(String source) throws JSONException {
        ArrayList<String> collectedMazes = new ArrayList<String>();
        JSONObject obj = new JSONObject(source);
        JSONArray mazes = obj.getJSONArray("mazes");

        for (int i = 0; i < mazes.length(); i++) {
            collectedMazes.add(mazes.getJSONObject(i).getString("code"));
        }

        return collectedMazes;
    }

    public static Point start(String source) throws JSONException {
        JSONObject obj = new JSONObject(source);
        return new Point(obj.getInt("x"), obj.getInt("y"));
    }

    public static FieldType moveResult(String source) throws JSONException {
        JSONObject obj = new JSONObject(source);
        String field = obj.getString("field");

        if (field.equals(".")) return FieldType.ROAD;
        else if (field.equals("@")) return FieldType.START;
        else if (field.equals("x")) return FieldType.FINISH;
        else return FieldType.ROAD;
    }

    public static int height(String mazeId, String source) throws JSONException{
        JSONObject obj = new JSONObject(source);
        JSONArray mazes = obj.getJSONArray("mazes");
        for (int i = 0; i < mazes.length(); i++) {
            if (mazes.getJSONObject(i).getString("code").equals(mazeId)) return mazes.getJSONObject(i).getInt("height");
        }
        return -1;
    }

    public static int width(String mazeId, String source) throws JSONException{
        JSONObject obj = new JSONObject(source);
        JSONArray mazes = obj.getJSONArray("mazes");
        for (int i = 0; i < mazes.length(); i++) {
            if (mazes.getJSONObject(i).getString("code").equals(mazeId)) return mazes.getJSONObject(i).getInt("width");
        }
        return -1;
    }

}
