package hu.krisztiaan.maze.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    private static final Logger log = Logger.getLogger(Server.class.getName());
    private static URL remoteServerUrl;

    public static String GetData(String urlAddress, String subUrl)
            throws HttpResponseException, MalformedURLException {

        try {

            log.log(Level.INFO, "Setting up connection to: " + urlAddress);

            remoteServerUrl = new URL(urlAddress + subUrl);
            HttpURLConnection conn = (HttpURLConnection) remoteServerUrl.openConnection();

            log.log(Level.INFO, "Sending request.");

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            log.log(Level.INFO, "HTTP response: "
                    + conn.getResponseCode() + " "
                    + conn.getResponseMessage());

            if (conn.getResponseCode() != 200) {
                throw new HttpResponseException("Failed : HTTP error code : "
                        + conn.getResponseCode(), conn.getResponseCode());
            }

            log.log(Level.INFO, "Creating BufferedReader from conn InputStream.");

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            log.log(Level.INFO, "Writing from BufferedReader to output");

            StringBuilder builder = new StringBuilder();
            String aux = "";

            while ((aux = br.readLine()) != null) {
                builder.append(aux);
            }

            log.log(Level.INFO, "Output ready. Content:\n--------\n" + builder.toString() + "\n--------");

            log.log(Level.INFO, "Disconnecting.");
            conn.disconnect();
            log.log(Level.INFO, "Disconnected.");

            return builder.toString();

        } catch (MalformedURLException e) {

            log.log(Level.SEVERE, urlAddress + " is not a valid URL. Exception:\n" + e.toString());
            throw e;

        } catch (IOException e) {

            log.log(Level.SEVERE, "IO error while getting data. Exception:\n" + e.toString());
            throw new RuntimeException(e);

        }
    }

    public static String PostData(String urlAddress, String subUrl, String input)
            throws HttpResponseException, MalformedURLException {

        try {

            log.log(Level.INFO, "Setting up connection to: " + urlAddress);

            remoteServerUrl = new URL(urlAddress + subUrl);
            HttpURLConnection conn = (HttpURLConnection) remoteServerUrl.openConnection();

            log.log(Level.INFO, "Sending request.");

            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            log.log(Level.INFO, "Writing to OutputStream.");

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            log.log(Level.INFO, "HTTP response: "
                    + conn.getResponseCode() + " "
                    + conn.getResponseMessage());

            if (conn.getResponseCode() != 200) {
                throw new HttpResponseException("Failed : HTTP error code : "
                        + conn.getResponseCode(), conn.getResponseCode());
            }

            log.log(Level.INFO, "Creating BufferedReader.");

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            log.log(Level.INFO, "Writing from BufferedReader to output.");

            StringBuilder builder = new StringBuilder();
            String aux = "";

            while ((aux = br.readLine()) != null) {
                builder.append(aux);
            }

            log.log(Level.INFO, "Result ready. Content:\n--------\n" + builder.toString() + "\n--------");

            log.log(Level.INFO, "Disconnecting.");
            conn.disconnect();
            log.log(Level.INFO, "Disconnected.");

            return builder.toString();

        } catch (MalformedURLException e) {

            log.log(Level.SEVERE, urlAddress + " is not a valid URL. Exception:\n" + e.toString());
            throw e;

        } catch (IOException e) {

            log.log(Level.SEVERE, "IO error while getting data. Exception:\n" + e.toString());
            throw new RuntimeException(e);

        }
    }
}
