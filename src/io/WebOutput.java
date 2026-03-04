package io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Concrete Web (HTTP) output.
 * Opens a temporary HTTP server on port 8081 and serves the given message as an
 * HTML page until one browser request is received.
 */
public class WebOutput {

    private static final int PORT = 8081;

    /** Serve the given message as a one-shot HTML page on port 8081. */
    public void show(String data) {
        try (ServerSocket server = new ServerSocket(PORT);
             Socket client = server.accept()) {

            String html = "<html><body style='font-family:sans-serif;margin:40px'>"
                    + "<h1>Web Output</h1><pre style='font-size:1.2em'>" + data + "</pre></body></html>";

            String response = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n"
                    + "Content-Length: " + html.getBytes().length + "\r\n"
                    + "Connection: close\r\n\r\n"
                    + html;

            client.getOutputStream().write(response.getBytes());
            client.getOutputStream().flush();
        } catch (IOException e) {
            // silently ignore
        }
    }
}
