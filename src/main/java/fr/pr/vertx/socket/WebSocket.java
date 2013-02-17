package fr.pr.vertx.socket;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpServerRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WebSocket {
    private static final String PATH = "/home/preliquet/IdeaProjects/ws/src/main/resources/ws.html";
    public static void main(String[] args) throws IOException {

        Vertx vertx = Vertx.newVertx();
        vertx.createHttpServer().websocketHandler(new SocketHandler()).requestHandler(new Handler<HttpServerRequest>() {
            public void handle(HttpServerRequest req) {
                if (req.path.equals("/"))
                    req.response.sendFile(PATH); // Serve the html
            }
        }).listen(8081);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
    }
}
