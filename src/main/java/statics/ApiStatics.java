package statics;

public class ApiStatics {
    public static final String IOSeparator = "\0";
    public static final String IOBlockSeparator = IOSeparator + IOSeparator;
    public static final String KeyValueSeparator = ":";

    public enum Keys {
        ConLen,
        Action,
        Token,
        Status,
    }
}
