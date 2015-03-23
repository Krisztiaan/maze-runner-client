package hu.krisztiaan.maze.rest;

/**
 * Created by Krisz on 2015.03.23..
 */
public class HttpResponseException extends Exception {
    int mResponseCode;

    public HttpResponseException(String message, int code) {
        super(message);
        mResponseCode = code;
    }

    public int getResponseCode() {
        return mResponseCode;
    }
}
