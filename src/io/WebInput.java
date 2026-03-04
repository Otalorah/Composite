package io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Concrete Web (HTTP) input.
 * Opens a temporary HTTP server on port 8080, waits for one GET request, and
 * parses the {@code value} query-parameter as an integer.
 *
 * Works with raw int values — adapted to the Input interface by AdapterWebInput.
 *
 * Example request: GET /?value=42 HTTP/1.1
 */
public class WebInput {

    private static final int PORT = 8080;

    /**
     * Start a one-shot HTTP listener and return the first {@code value}
     * parameter received, or 0 if none is found / an error occurs.
     */
    public int read() {
        try (ServerSocket server = new ServerSocket(PORT);
             Socket client = server.accept();
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(client.getInputStream()))) {

            // Read the first line of the HTTP request: "GET /?value=42 HTTP/1.1"
            String requestLine = reader.readLine();
            sendHttpResponse(client, "Value received. You can close this tab.");

            if (requestLine != null && requestLine.contains("value=")) {
                String[] parts = requestLine.split("value=");
                String valueStr = parts[1].split("[ &]")[0];
                return Integer.parseInt(valueStr.trim());
            }
        } catch (IOException | NumberFormatException e) {
            // silently ignore
        }
        return 0;
    }

    private void sendHttpResponse(Socket client, String body) {
        try {
            String response = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/plain\r\n"
                    + "Content-Length: " + body.length() + "\r\n"
                    + "Connection: close\r\n\r\n"
                    + body;
            client.getOutputStream().write(response.getBytes());
            client.getOutputStream().flush();
        } catch (IOException ignored) {
        }
    }
}
