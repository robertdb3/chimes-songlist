package src.dl;

import freemarker.template.*;
import java.util.*;
import java.io.*;
import java.net.URLDecoder;
import com.sun.net.httpserver.HttpExchange;

public class DisplayLogic {
    private Configuration cfg;
    public DisplayLogic() throws Exception {
        cfg = new Configuration(Configuration.VERSION_2_3_32);
        cfg.setDirectoryForTemplateLoading(new File("resources/templates"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
        cfg.setSQLDateAndTimeTimeZone(TimeZone.getDefault());
    }

    public void parseTemplate(String templateName, Map<String, Object> dataModel, Writer out) {
        Template template;
        try {
            template = cfg.getTemplate(templateName);
            template.process(dataModel, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> parseResponse(HttpExchange exchange) {
        Map<String, String> myMap = new HashMap<String, String>();

        // the data sent via the HTML form ends up in the request body
        byte[] b;
        try {
            b = exchange.getRequestBody().readAllBytes();
        } catch (Exception e) {
            e.printStackTrace();
            return myMap;
        }
        String formData = new String(b);
        if (formData.equals("")) {
            return myMap;
        }

        try {
            String[] pairs = formData.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                myMap.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"),
                        URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myMap;
    }
}
