package games.negative.alumina.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Represents a utility class for HTTP requests.
 */
public class HTTPUtil {

    /**
     * Get the response from a GET request.
     * @param url The URL to send the request to.
     * @return The response.
     * @throws IOException If an error occurs while sending the request.
     */
    public static JsonObject get(final String url) throws IOException {
        URL httpUrl = new URL(url);

        // Open a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();

        // Set request method to GET
        connection.setRequestMethod("GET");

        // Get the response code
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Read the response data

            try (Reader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                // Parse the JSON response using Gson
                return JsonParser.parseReader(reader).getAsJsonObject();
            }
        }
        return null;
    }

    /**
     * Send a POST request.
     * @param url The URL to send the request to.
     * @param body The body of the request.
     * @return The response.
     * @throws IOException If an error occurs while sending the request.
     */
    public static JsonObject post(final String url, final String body) throws IOException {
        URL httpUrl = new URL(url);

        // Open a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();

        // Set request method to POST
        connection.setRequestMethod("POST");

        // Set the content type
        connection.setRequestProperty("Content-Type", "application/json; utf-8");

        // Enable output
        connection.setDoOutput(true);

        // Write the body
        try (var writer = connection.getOutputStream()) {
            writer.write(body.getBytes());
        }

        // Get the response code
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Read the response data

            try (Reader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                // Parse the JSON response using Gson
                return JsonParser.parseReader(reader).getAsJsonObject();
            }
        }
        return null;
    }
}
