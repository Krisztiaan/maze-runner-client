package hu.krisztiaan.maze.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GetData {
    private static final Logger log = Logger.getLogger(GetData.class.getName());
    private static URL remoteServerUrl;

    public static String GetData(String urlAddress, String subUrl)
            throws HttpResponseException, MalformedURLException {

        try {

            log.log(Level.FINE, "Setting up connection to: " + urlAddress);

            remoteServerUrl = new URL(urlAddress + subUrl);
            HttpURLConnection conn = (HttpURLConnection) remoteServerUrl.openConnection();

            log.log(Level.FINE, "Sending request.");

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            log.log(Level.FINE, "HTTP response: "
                    + conn.getResponseCode() + " "
                    + conn.getResponseMessage());

            if (conn.getResponseCode() != 200) {
                throw new HttpResponseException("Failed : HTTP error code : "
                        + conn.getResponseCode(), conn.getResponseCode());
            }

            log.log(Level.FINE, "Writing from BufferedReader to output");

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            log.log(Level.FINE, "Writing from BufferedReader to output");

            String output = "";

            while ((output += br.readLine() + "\n") != null) {
                log.log(Level.FINE, ".");
            }

            log.log(Level.FINE, "Output ready. Content:\n--------" + output + "--------");

            log.log(Level.FINE, "Disconnecting.");
            conn.disconnect();
            log.log(Level.FINE, "Disconnected.");

            return output;

        } catch (MalformedURLException e) {

            log.log(Level.SEVERE, urlAddress + " is not a valid URL. Exception:\n" + e.toString());
            throw e;

        } catch (IOException e) {

            log.log(Level.SEVERE, "IO error while getting data. Exception:\n" + e.toString());
            throw new RuntimeException(e);

        }
    }

}
