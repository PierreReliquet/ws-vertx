package fr.pr.vertx.socket;

import fr.pr.vertx.model.Anniversaries;
import fr.pr.vertx.model.Person;
import org.vertx.java.core.Handler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.ServerWebSocket;

import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

public class SocketHandler implements Handler<ServerWebSocket>, Observer {

    private Set<ServerWebSocket> webSockets = new HashSet<ServerWebSocket>();

    public SocketHandler() {
        Anniversaries.getInstance().addObserver(this);
    }

    public static final String WS_PATH = "/anniversaries";
    @Override
    public void handle(ServerWebSocket ws) {
        if (ws.path.equals(WS_PATH)) {
            webSockets.add(ws);
            ws.dataHandler(new DataHandler());
        } else {
            ws.reject();
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        StringBuilder global = new StringBuilder();
        for(Person p : Anniversaries.getInstance().getPersons()) {
            global.append(p.manualSerialization() + "|");
        }
        for(ServerWebSocket ws : webSockets) {
            ws.writeTextFrame(global.toString());
        }
    }

    class DataHandler implements Handler<Buffer> {
        @Override
        public void handle(Buffer data) {
            Anniversaries.getInstance().addPerson(data.toString());
        }
    }
}
