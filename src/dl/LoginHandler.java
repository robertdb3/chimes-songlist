package src.dl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class LoginHandler implements HttpHandler {
    private DisplayLogic displayLogic;
    private final String FORM_PAGE = "login.thtml";

    public LoginHandler(DisplayLogic displayLogic) {
        this.displayLogic = displayLogic;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Map<String, Object> dataModel = new HashMap<String, Object>();

        Map<String, String> dataFromWebForm = displayLogic.parseResponse(exchange);

        if (dataFromWebForm.containsKey("username")) {
            dataModel.put("username", dataFromWebForm.get("username"));
        }

        StringWriter sw = new StringWriter();
        displayLogic.parseTemplate(FORM_PAGE, dataModel, sw);
        exchange.getResponseHeaders().set("Content-Type", "text/html");

        exchange.sendResponseHeaders(200, (sw.getBuffer().length()));

        OutputStream os = exchange.getResponseBody();
        os.write(sw.toString().getBytes());
        os.close();
    }
}
