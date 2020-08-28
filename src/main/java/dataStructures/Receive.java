package dataStructures;

import error.ReadonlyAttributeException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class Receive {
    static Map<String, Object> parseStringToMap(String string) {
        throw new NotImplementedException();
    }

    private final Map<String, Object> header;
    private Map<String, Object> body = null;

    public Receive(Map<String, Object> header) {
        this.header = header;
    }

    public Receive(Map<String, Object> header, Map<String, Object> body) throws ReadonlyAttributeException {
        this(header);
        this.setBody(body);
    }

    public Receive(String header) {
        this(Receive.parseStringToMap(header));
    }

    public Receive(String header, String body) throws ReadonlyAttributeException {
        this(Receive.parseStringToMap(header), Receive.parseStringToMap(body));
    }

    public void setBody(Map<String, Object> body) throws ReadonlyAttributeException {
        if (body != null) {
            throw new ReadonlyAttributeException();
        } else {
            this.body = body;
        }
    }

    public void setBody(String body) {
        this.body = Receive.parseStringToMap(body);
    }

    public Map<String, Object> getHeader() {
        return header;
    }

    public Map<String, Object> getBody() {
        return body;
    }
}
