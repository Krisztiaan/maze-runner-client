package hu.krisztiaan.maze.maze;

/**
 * Created by Krisz on 2015.03.23..
 */
public enum FieldType {
    UNKNOWN,
    ROAD,
    TAKEN_ROAD,
    SOLUTION,
    WALL,
    START,
    FINISH;

    @Override
    public String toString() {
        switch (this) {
            case UNKNOWN:
                return "?";
            case ROAD:
                return ".";
            case TAKEN_ROAD:
                return ",";
            case WALL:
                return "#";
            case START:
                return "@";
            case FINISH:
                return "x";
            case SOLUTION:
                return "*";
            default:
                return "?";
        }
    }
}
