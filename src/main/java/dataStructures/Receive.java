package dataStructures;

import java.util.Map;

public class Recieve {
    private final Map<String, Object> header;
    private final Map<String, Object> body;

    public Recieve(Map<String, Object> header, Map<String, Object> body){
        this.header = header;
        this.body = body;
    }

    public Map<String, Object> getHeader() {
        return header;
    }

    public Map<String, Object> getBody() {
        return body;
    }
}
