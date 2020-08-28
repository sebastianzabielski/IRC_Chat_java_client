package dataStructures;

import error.ReadonlyAttributeException;
import statics.ApiStatics;

import java.util.HashMap;
import java.util.Map;

public class Receive {
    public static Map<String, Object> parseStringToMap(String string) {
        Map<String, Object> result = new HashMap<>();
        String[] asd = string.split(ApiStatics.IOSeparator);

        for (String item : asd) {
            String[] splitItem = item.split(ApiStatics.KeyValueSeparator);
            try {
                Object value = Receive.parseStringToJavaType(splitItem[1]);
                result.put(splitItem[0], value);
            } catch (ArrayIndexOutOfBoundsException e) {
                result.put(item, null);
                e.printStackTrace();
            }

        }
        return result;
    }

    public static Object parseStringToJavaType(String item) {
        try {
            if (item == null || item.equals("null")) {
                return null;
            }
        } catch (Exception ignored) {
        }
        try {
            return Integer.parseInt(item);
        } catch (Exception ignored) {
        }
        try {
            return Boolean.parseBoolean(item);
        } catch (Exception ignored) {
        }
        return item;
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
        if (this.body != null) {
            throw new ReadonlyAttributeException();
        } else {
            this.body = body;
        }
    }

    public void setBody(String body) throws ReadonlyAttributeException {
        this.setBody(Receive.parseStringToMap(body));
    }

    public Map<String, Object> getHeader() {
        return header;
    }

    public Map<String, Object> getBody() {
        return body;
    }
}
