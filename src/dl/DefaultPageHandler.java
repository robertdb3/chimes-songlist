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

public class DefaultPageHandler implements HttpHandler {
    private DisplayLogic displayLogic;
    private final String DEFAULT_PAGE = "index.thtml";

    public DefaultPageHandler(DisplayLogic displayLogic) {
        this.displayLogic = displayLogic;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        StringWriter sw = new StringWriter();
        // dataModel will hold the data to be used in the template
        Map<String, Object> dataModel = new HashMap<String, Object>();

        {
            // I'm putting this in a code block because it's really just demo
            // code. We're populating the dataModel with some example data
            // that's not particularly useful

            // the "date" variable in the template will be set to the current date
            dataModel.put("date", new Date().toString());
            // and randvector will be a vector of random doubles (just for illustration)
            Vector<Double> v = new Vector<Double>();
            for (int i = 0; i < 10; i++) {
                v.add(Math.random());
            }
            dataModel.put("randvector", v);
        }

        // now we call the display method to parse the template and write the output
        displayLogic.parseTemplate(DEFAULT_PAGE, dataModel, sw);

        // set the type of content (in this case, we're sending back HTML)
        exchange.getResponseHeaders().set("Content-Type", "text/html");
        // send the HTTP headers
        exchange.sendResponseHeaders(200, (sw.getBuffer().length()));
        // finally, write the actual response (the contents of the template)
        OutputStream os = exchange.getResponseBody();
        os.write(sw.toString().getBytes());
        os.close();
    }
}
