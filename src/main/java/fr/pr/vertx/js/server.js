load('vertx.js')

var anniversaries = "";

var sockets = new Array();

function replaceAll(txt, replace, with_this) {
  return txt.replace(new RegExp(replace, 'g'),with_this);
}

vertx.createHttpServer().websocketHandler(
    function(ws) {
        sockets.push(ws);
        ws.writeTextFrame(anniversaries);
        ws.dataHandler(
            function(buffer) {
                anniversaries += replaceAll(buffer.toString(), "\\|", " ") + "|";
                for(var i = 0; i < sockets.length; i++) {
                    sockets[i].writeTextFrame(anniversaries);
                }
               // ws.writeTextFrame(anniversaries);
            }
        );
    }
).requestHandler(
    function(req) {
        if (req.uri == "/") req.response.sendFile("../../../../../resources/ws.html")
    }
).listen(8081)