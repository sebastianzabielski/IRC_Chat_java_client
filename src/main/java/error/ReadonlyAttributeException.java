package error;

public class ReadonlyAttributeException extends Exception {
    public ReadonlyAttributeException() {
        super("ATTRIBUTE_ALREADY_SET");
    }
}
