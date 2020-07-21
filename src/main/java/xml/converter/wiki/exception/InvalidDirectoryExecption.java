package xml.converter.wiki.exception;

public class InvalidDirectoryExecption extends RuntimeException {
    public InvalidDirectoryExecption() {
    }

    public InvalidDirectoryExecption(String message) {
        super(message);
    }

    public InvalidDirectoryExecption(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDirectoryExecption(Throwable cause) {
        super(cause);
    }

    public InvalidDirectoryExecption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
