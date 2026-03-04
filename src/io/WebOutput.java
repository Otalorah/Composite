package io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Concrete Web (HTTP) output.
 * Opens a temporary HTTP server on port 8081 and serves the given integer as an
 * HTML page until one browser request is received.
 *
 * Works with raw int values — adapted to the Output interface by AdapterWebOutput.
 */
public class WebOutput {

    private static final int PORT = 8081;

    /** Serve the given integer as a one-shot HTML page on port 8081. */
    public void show(int data) {
        System.out.println("[Web] Serving output at http://localhost:" + PORT + " ...");
        try (ServerSocket server = new ServerSocket(PORT);
             Socket client = server.accept()) {

            String html = "<html><body style='font-family:sans-serif;text-align:center;margin-top:60px'>"
                    + "<h1>Web Output</h1><p style='font-size:3em'>" + data + "</p></body></html>";

            String response = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n"
                    + "Content-Length: " + html.length() + "\r\n"
                    + "Connection: close\r\n\r\n"
                    + html;

            client.getOutputStream().write(response.getBytes());
            client.getOutputStream().flush();
            System.out.println("[Web] Response sent: " + data);
        } catch (IOException e) {
            System.err.println("[Web] Output error: " + e.getMessage());
        }
    }
}
