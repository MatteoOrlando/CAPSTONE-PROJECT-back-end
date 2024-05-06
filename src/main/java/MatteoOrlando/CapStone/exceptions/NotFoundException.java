package MatteoOrlando.CapStone.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(long id) {
        super("elemento con " + id + " non trovato!");

    }

    public NotFoundException(String message) {
        super(message);
    }
}