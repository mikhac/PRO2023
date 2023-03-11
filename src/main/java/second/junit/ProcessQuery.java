package second.junit;

import java.util.Locale;

public class ProcessQuery {

    HttpQueryClass queryClass;

    public ProcessQuery(HttpQueryClass queryClass) {
        this.queryClass = queryClass;
    }

    public String process() {
        return queryClass.query("https://api.gios.gov.pl/pjp-api/rest/station/findAll").toUpperCase();
    }

}
