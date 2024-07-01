package src.dl;

import freemarker.core.ParseException;
import freemarker.template.*;
import java.util.*;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import com.sun.net.httpserver.HttpExchange;

public class DisplayLogic {
    private Configuration cfg;
    public DisplayLogic() throws IOException {
        cfg = new Configuration(Configuration.VERSION_2_3_32);
        cfg.setDirectoryForTemplateLoading(new File("resources/templates"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
        cfg.setSQLDateAndTimeTimeZone(TimeZone.getDefault());
    }
}
